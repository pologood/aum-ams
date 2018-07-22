package com.aum.ams.pinan;

import com.github.peacetrue.util.ClassLoaderUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.TraceClassVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * asm工具类
 *
 * @author xiayx
 */
public abstract class AsmUtils {

    /**
     * 打印字节码内容
     *
     * @param className 类名
     */
    public static void printContent(String className) {
        try {
            ClassReader cr = new ClassReader(className);
            cr.accept(new TraceClassVisitor(new PrintWriter(System.out)), ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
            throw new IllegalArgumentException(className, e);
        }
    }

    /**
     * 打印字节码内容
     *
     * @param content 二进制内容
     */
    public static void printContent(byte[] content) {
        ClassReader cr = new ClassReader(content);
        cr.accept(new TraceClassVisitor(new PrintWriter(System.out)), ClassReader.EXPAND_FRAMES);
    }

    public static <T> Stream<T> asStream(Iterator<? extends T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    public static MethodInsnNode toMethodInsnNode(int opcode, Method method) {
        return new MethodInsnNode(opcode,
                Type.getInternalName(method.getDeclaringClass()), method.getName(),
                Type.getMethodDescriptor(method), method.getDeclaringClass().isInterface());
    }

    public static boolean isMethodOf(MethodInsnNode methodInsnNode, Class<?> typeClass, String name) {
        return methodInsnNode.owner.equals(Type.getInternalName(typeClass))
                && methodInsnNode.name.equals(name);
    }

    public static boolean isMethodOf(MethodInsnNode methodInsnNode, Method method) {
        return methodInsnNode.owner.equals(Type.getInternalName(method.getDeclaringClass()))
                && methodInsnNode.name.equals(method.getName())
                && methodInsnNode.desc.equals(Type.getMethodDescriptor(method));
    }

    public static boolean isConstructorOf(MethodInsnNode methodInsnNode, Class<?> typeClass) {
        return isMethodOf(methodInsnNode, typeClass, "<init>");
    }

    public static boolean isConstructorOf(MethodInsnNode methodInsnNode, Constructor constructor) {
        return methodInsnNode.owner.equals(Type.getInternalName(constructor.getDeclaringClass()))
                && methodInsnNode.name.equals("<init>")
                && methodInsnNode.desc.equals(Type.getConstructorDescriptor(constructor));
    }

    public static void removeInsnNode(InsnList insnList, AbstractInsnNode node, Predicate<AbstractInsnNode> until) {
        if (node.getNext() != null && !until.test(node)) {
            removeInsnNode(insnList, node.getNext(), until);
        }
        logger.trace("\t\tremove node: {} {}", node.getClass().getSimpleName(), node.getOpcode());
        insnList.remove(node);
    }

    /** 获取输入流 */
    public static InputStream getResourceAsStream(String path) throws FileNotFoundException {
        return path.startsWith("/")
                ? new FileInputStream(path)
                : AsmUtils.class.getResourceAsStream("/" + path);
    }

    /** 获取输入流 */
    public static InputStream getResourceAsStream(File file) throws FileNotFoundException {
        return getResourceAsStream(file.getPath());
    }

    /** 将二进制内容写入字节码文件 */
    public static void write(String packageName, String classSimpleName, byte[] content) {
        if (!packageName.startsWith("/")) {
            packageName = packageName.replace(".", "/");
            packageName = AsmUtils.class.getResource("/" + packageName).getPath();
        }
        Path path = Paths.get(packageName, classSimpleName + ".class");
        try {
            Files.write(path, content);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    private static Logger logger = LoggerFactory.getLogger(AsmUtils.class);

    public static byte[] changePingAn(String className) throws IOException {
        logger.trace("className: {}", className);
//        printContent(className);
        className = className.replace('.', '/');
        className = "/" + className + ".class";
        logger.trace("classLoader: {}", AsmUtils.class.getClassLoader());
        InputStream resourceAsStream = AsmUtils.class.getResourceAsStream(className);
        ClassReader classReader = new ClassReader(resourceAsStream);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("<init>")) continue;
            logger.trace("\tmethod: {}{}", method.name, method.desc);

            AbstractInsnNode[] abstractInsnNodes = method.instructions.toArray();
            logger.trace("\t\tinsn nodes.length:{}", abstractInsnNodes.length);


            //remove new FileInputStream
            Arrays.stream(abstractInsnNodes)
                    .filter(abstractInsnNode -> abstractInsnNode.getOpcode() == Opcodes.NEW)
                    .map(abstractInsnNode -> (TypeInsnNode) abstractInsnNode)
                    .filter(typeInsnNode -> typeInsnNode.desc.equals(Type.getInternalName(FileInputStream.class)))
                    .forEach(typeInsnNode -> {
                        logger.trace("\t\tremove new {}", typeInsnNode.desc);
                        method.instructions.remove(typeInsnNode.getNext());
                        method.instructions.remove(typeInsnNode);
                    });
            //replace FileInputStream.<init> to AsmUtils.getResourceAsStream
            Arrays.stream(abstractInsnNodes)
                    .filter(abstractInsnNode -> abstractInsnNode.getOpcode() == Opcodes.INVOKESPECIAL)
                    .map(abstractInsnNode -> (MethodInsnNode) abstractInsnNode)
                    .filter(methodInsnNode -> AsmUtils.isConstructorOf(methodInsnNode, FileInputStream.class))
                    .forEach(methodInsnNode -> {
                        String _className = Type.getMethodType(methodInsnNode.desc).getArgumentTypes()[0].getClassName();
                        Class<?> argumentType = ClassUtils.resolveClassName(_className, AsmUtils.class.getClassLoader());
                        MethodInsnNode newInsnNode = AsmUtils.toMethodInsnNode(Opcodes.INVOKESTATIC,
                                ReflectionUtils.findMethod(AsmUtils.class, "getResourceAsStream", argumentType));
                        logger.trace("\t\treplace {}.{}{} to {}.{}{}",
                                methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc,
                                newInsnNode.owner, newInsnNode.name, newInsnNode.desc);
                        method.instructions.set(methodInsnNode, newInsnNode);
                    });
            Arrays.stream(abstractInsnNodes)
                    .filter(node -> node.getOpcode() == Opcodes.INVOKEVIRTUAL)
                    .map(node -> (MethodInsnNode) node)
                    .filter(methodInsnNode -> AsmUtils.isMethodOf(methodInsnNode, FileInputStream.class, "close"))
                    .forEach(methodInsnNode -> {
                        method.instructions.remove(methodInsnNode.getPrevious());
                        method.instructions.remove(methodInsnNode);
                        logger.trace("\t\tremove java/io/FileInputStream.close ()V");
                    });
            //remove throw new IOException and new IllegalArgumentException
            Arrays.stream(abstractInsnNodes)
                    .filter(abstractInsnNode -> abstractInsnNode.getOpcode() == Opcodes.NEW)
                    .map(abstractInsnNode -> (TypeInsnNode) abstractInsnNode)
                    .filter(typeInsnNode ->
                            typeInsnNode.desc.equals(Type.getInternalName(IOException.class))
                                    || typeInsnNode.desc.equals(Type.getInternalName(IllegalArgumentException.class))
                    )
                    .forEach(typeInsnNode -> {
                        removeInsnNode(method.instructions, typeInsnNode, node -> node.getOpcode() == Opcodes.ATHROW);
                    });

            // change variable type: from FileInputStream to InputStream
            method.localVariables.forEach(localVariableNode -> {
                if (localVariableNode.desc.equals(Type.getDescriptor(FileInputStream.class))) {
                    logger.trace("\t\tchange type of variable {}: from {} to {}",
                            localVariableNode.name, localVariableNode.desc, Type.getDescriptor(InputStream.class)
                    );
                    localVariableNode.desc = Type.getDescriptor(InputStream.class);
                }
            });

        }

        ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }

    public static Class loadPingAnChanged(String className) throws IOException {
        return ClassLoaderUtils.defineClass(className, changePingAn(className));
    }

}
