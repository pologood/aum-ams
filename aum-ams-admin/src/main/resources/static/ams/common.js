//------------接口
class Module {
    constructor() {
    }

    /**@return {String}*/
    getName() {}

    /**@return {String}*/
    getPath() {}

    /**
     * @param name {String}
     * @return {Operate}
     */
    getOperate(name) {}

    /**@return {Operate}*/
    getQueryOperate() {}

    /**@return {Operate}*/
    getAddOperate() {}

    /**@return {Operate}*/
    getViewOperate() {}

    /**@return {Operate}*/
    getModifyOperate() {}

    /**@return {Operate}*/
    getDeleteOperate() {}
}

class Operate {
    constructor() {
    }

    /**@return {Module}*/
    getModule() {}

    /**@return {String}*/
    getName() {}

    /**
     * @param [options] {object}
     * @return {Window}
     */
    turn(options) {}

    /**
     * @param options {object}
     * @return {Promise}
     */
    request(options) {}
}

Operate.QUERY = "query";
Operate.ADD = "add";
Operate.VIEW = "view";
Operate.MODIFY = "modify";
Operate.DELETE = "delete";

class Record {
    constructor() {
    }

    /**@return {Module}*/
    getModule() {}

    getId() {}

    /**@return {Object}*/
    getData() {}

    /**
     * @param operate {Operate}
     * @param options {Object}
     * @return {Promise}
     */
    request(operate, options) {}

    /**
     * @param operate {Operate}
     * @param [options] {Object}
     * @return {Window}
     */
    turn(operate, options) {}

    /**
     * @param [options] {Object}
     * @return {Promise}
     */
    loadData(options) {}
}

class Metadata {

    constructor() {
    }

    /**
     * @return {Module}
     */
    getModule() {}

    /**
     * @return {Record}
     */
    getRecord() {}

    /**
     * @return {Operate}
     */
    getOperate() {}
}


//------------实现

class SimpleModule extends Module {
    constructor(name, path) {
        super();
        this.name = name;
        this.path = path;
    }

    getName() {
        return this.name;
    }

    getPath() {
        return this.path || ("/" + this.getName());
    }

    /**@return {SimpleOperate}*/
    getOperate(name) {
        return new SimpleOperate(this, name);
    }

    /**@return {SimpleOperate}*/
    getQueryOperate() {
        return this.getOperate(Operate.QUERY);
    }

    /**@return {SimpleOperate}*/
    getAddOperate() {
        return this.getOperate(Operate.ADD);
    }

    /**@return {SimpleOperate}*/
    getViewOperate() {
        return this.getOperate(Operate.VIEW);
    }

    /**@return {SimpleOperate}*/
    getModifyOperate() {
        return this.getOperate(Operate.MODIFY);
    }

    /**@return {SimpleOperate}*/
    getDeleteOperate() {
        return this.getOperate(Operate.DELETE);
    }
}

class SimpleOperate extends Operate {
    constructor(module, name, path) {
        super();
        this.module = module;
        this.name = name;
        this.path = path;
    }

    getModule() {
        return this.module;
    }

    getName() {
        return this.name;
    }

    /**
     * @param [options] {object}
     * @return {Window}
     */
    turn(options) {
        options = _.extend({target: '_blank'}, options);
        let uri = this.module.getPath() + "/" + this.getName() + ".html";
        if (options) uri += "?" + Qs.stringify(options.params, {arrayFormat: 'repeat'});
        return window.open(uri, options.target);
    }

    /**@return {Promise} */
    request(options) {
        console.info("SimpleOperate.request: {this: ", this, ",options: ", options, "}");
        options.url = this.module.getPath();
        switch (this.getName()) {
            case Operate.QUERY:
                options.method = 'GET';
                delete options.data;
                break;
            case Operate.VIEW:
                options.method = 'GET';
                options.url += "/" + options.params.id;
                delete options.data;
                break;
            case Operate.ADD:
                options.method = 'POST';
                break;
            case Operate.MODIFY:
                options.method = 'PUT';
                break;
            case Operate.DELETE:
                options.method = 'DELETE';
                delete options.data;
                break;
        }
        if (options.params && options.data) {
            options.data.id && (delete options.params.id);
        }
        let promise = axios.request(options);
        return promise;
    }
}


class SimpleRecord extends Record {

    /**
     * @param [module] {Module}
     * @param [id] {Number|String|Array<Number|String>}
     * @param [data] {Object|Array<Object>}
     */
    constructor(module, id, data) {
        super();
        this.module = module;
        this.id = id;
        this.data = data;
    }

    /**@return {SimpleModule}*/
    getModule() {return this.module;}

    getId() {
        if (this.id) return this.id;
        if (this.data) return Array.isArray(this.data) ? this.data.map(item => item.id) : this.data.id;
        return null;
    }

    getData() {
        return this.data;
    }

    /**
     * @param operate {Operate}
     * @param [options] {Object}
     * @return {Promise}
     */
    request(operate, options) {
        console.info("SimpleRecord.request: {operate: ", operate, ", options: ", options, "}");
        options = _.extend({params: {id: this.getId()}, data: this.getData()}, options);
        return operate.request(options);
    }

    /**
     * @param operate {Operate}
     * @param [options] {Object}
     * @return {Window}
     */
    turn(operate, options) {
        console.info("SimpleRecord.turn: {operate: ", operate, ", options: ", options, "}");
        options = _.extend({params: {id: this.getId()}}, options);
        return operate.turn(options);
    }

    loadData(options) {
        if (this.data) return new Promise(resolved => resolved(this.data));
        let operate = this.getModule().getViewOperate();
        let promise = this.request(operate, options);
        promise.then(response => {this.data = response.data});
        return promise;
    }
}


class SimpleMetadata extends Metadata {
    constructor(module, operate, record) {
        super();
        this.module = module;
        this.operate = operate;
        this.record = record;
    }

    /**@return {SimpleModule}*/
    getModule() {
        return this.module;
    }

    /**@return {SimpleRecord}*/
    getRecord() {
        return this.record;
    }

    /**@return {SimpleOperate}*/
    getOperate() {
        return this.operate;
    }
}


/**
 * @return {SimpleMetadata}
 */
SimpleMetadata.fromLocation = function () {
    let parts = location.pathname.split('.', 2).shift().split("/");
    let params = new URLSearchParams(location.search);
    let module = new SimpleModule(parts[1]);
    let record = new SimpleRecord(module, params.get("id"));
    let pageOperate = {'list': 'query', 'detail': 'view'};
    let operate = new SimpleOperate(module, pageOperate[parts[2]] || parts[2]);
    let simpleMetadata = new SimpleMetadata(module, operate, record);
    console.info("build Metadata from location: ", simpleMetadata);
    return simpleMetadata;
};

//使用window.open时，在opener和opened窗体间的事件交互
let WindowInteraction = {
    _events(_window) {
        //使用长变量名``WindowInteractionEvents``避免重名
        if (!_window.WindowInteractionEvents) {
            _window.WindowInteractionEvents = {};
        }
        return _window.WindowInteractionEvents;
    },
    bind(_window, name, callback) {
        console.info('WindowInteraction.bind: ', _window, name, callback);
        let events = WindowInteraction._events(_window);
        if (typeof name === 'string') {
            if (!events[name]) events[name] = [];
            events[name].push(callback);
        } else if (Array.isArray(name)) {
            name.forEach(t => WindowInteraction.bind(_window, t, callback));
        }
    },
    trigger(_window, name, options) {
        console.info('WindowInteraction.trigger: ', _window, name, options);
        if (!(_window instanceof Window)) {
            options = name;
            name = _window;
            _window = window;
        }
        let events = WindowInteraction._events(_window);
        if (typeof name === 'string') {
            if (!events[name]) return;
            events[name].forEach(callback => callback(options));
        } else if (Array.isArray(name)) {
            name.forEach(t => WindowInteraction.trigger(_window, t, options));
        }
    }
};


let ListVue = Vue.extend({
    el: '#layout',
    data: function () {
        return {
            //查询条件
            condition: {
                rules: {},
                params: {
                    page: 0,
                    size: 10,
                    sort: null
                },
            },
            //查询结果
            result: {
                columns: [],
                page: {totalElements: 0, content: []},//分页数据
            },
            loading: false,//是否加载中
            selection: [],//选中的数据
        };
    },
    methods: {
        initSort() {
            this.result.columns.filter(t => t.sortType).forEach(t => {
                this.condition.params.sort = t.key + ',' + t.sortType;
            });
        },
        backupParams() {
            //保存初始化副本，重置时使用
            this.backup = {
                page: this.condition.params.page,
                size: this.condition.params.size,
                sort: this.condition.params.sort,
            };
        },
        query(reset) {
            console.info("ListView.query: ", reset);
            if (reset) this.condition.params.page = this.backup.page;
            this.loading = true;
            let options = {params: this.condition.params, showMessage: true};
            let promise = this.metadata.getOperate().request(options);
            promise.then(response => {
                this.loading = false;
                this.result.page = response.data;
            });
            return promise;
        },
        reset() {
            this.$refs.condition.resetFields();
            return this.query(true);
        },
        onPageChange(page) {
            this.condition.params.page = page - 1;
            this.query();
        },
        onPageSizeChange(size) {
            this.condition.params.size = size;
            this.query(true);
        },
        buildSpan(h, params, mapper) {
            let value = params.row[params.column.key];
            return h('span', mapper ? mapper(value, params.row) : value);
        },
        buildBtn(h, params, options) {
            return h('Button', {
                props: {type: 'primary', size: 'small'},
                style: {marginRight: '5px'},
                on: {click: () => options.click(params)}
            }, options.text);
        },
        buildAddBtn(h, params) {
            let options = {text: '新增', click: () => this.toAddPage(params.index)};
            return this.buildBtn(h, params, options);
        },
        buildModifyBtn(h, params) {
            let options = {text: '修改', click: () => this.toModifyPage(params.index)};
            return this.buildBtn(h, params, options);
        },
        toAddPage() {
            let _window = this.metadata.getModule().getAddOperate().turn();
            WindowInteraction.bind(_window, 'operateSuccess', () => this.query());
            return _window;
        },
        toViewPage(index) {
            return new SimpleRecord(null, null, this.result.page.content[index])
                .turn(this.metadata.getModule().getViewOperate());
        },
        toModifyPage(index) {
            let _window = new SimpleRecord(null, null, this.result.page.content[index])
                .turn(this.metadata.getModule().getModifyOperate());
            WindowInteraction.bind(_window, 'operateSuccess', () => this.query());
            return _window;
        },
        onSortChange(options) {
            console.info('ListVue.onSortChange:', options);
            this.condition.params.sort =
                options.order === 'normal'
                    ? this.backup.sort
                    : options.key + ',' + options.order;
            return this.query(true);
        },
        onSelectionChange(selection) {
            this.selection = selection;
        },
        remove(index, item) {
            console.info('ListView.remove:', index, item);
            if (!index || index.length === 0 || !item || item.length === 0)
                return this.$Message.warning('尚未选中任何记录!');
            index = this.toArray(index);
            item = this.toArray(item);
            let module = this.metadata.module;
            let promise = new SimpleRecord(null, null, item)
                .request(module.getDeleteOperate());
            promise.then(t => index.sort((left, right) => right - left).forEach(i => this.result.page.content.splice(i, 1)));
            return promise;
        },
        removeByIndex(index) {
            return this.remove(index, this.result.page.content[index]);
        },
        removeByItems(items) {
            console.info('ListView.removeByItems:', items);
            let promise = this.remove(items.map(t => _.findIndex(this.result.page.content, t)).filter(t => t !== -1), items);
            promise && promise.then(t => items.splice(0, items.length));
            return promise;
        },
        toArray(value) {
            return Array.isArray(value) ? value : [value];
        }
    },
    beforeCreate() {
        this.metadata = SimpleMetadata.fromLocation();
    },
    created() {
        ResultHandler.register({messager: this.$Message});
        this.initSort();
        this.backupParams();
        this.query();
    }
});


let DetailVue = Vue.extend({
    el: '#layout',
    data: function () {
        return {
            rules: {},
            record: {},
        };
    },
    methods: {
        submit(name) {
            this.$refs[name].validate((valid) => {
                if (!valid) return;
                let record = this.metadata.getRecord();
                let promise = record.request(this.metadata.getOperate(), {showSuccessMessage: true});
                promise.then(t => {
                    if (t.isSuccess()) {
                        record.id = record.id || t.data;
                        WindowInteraction.trigger("operateSuccess");
                        setTimeout(() => record.turn(this.metadata.getModule().getViewOperate(), {target: '_self'}), 1000)
                    }
                });
            });
        },
        reset(name) {
            this.$refs[name].resetFields();
        }
    },
    beforeCreate() {
        this.metadata = SimpleMetadata.fromLocation();
        this.isView = this.metadata.getOperate().getName() === Operate.VIEW;
    },
    created() {
        ResultHandler.register({messager: this.$Message});
        let record = this.metadata.getRecord();
        if (record.getId()) {
            let promise = record.loadData().then(() => this.record = record.getData());
            if (this.isView) promise.then(t => new View(document.forms.record).init());
        } else {
            record.data = this.record;
        }
    }
});

//--------------async-validator

AsyncValidator = function () {};
AsyncValidator.messages = newMessages();
AsyncValidator.ruleNames = _.flatMap(_.keys(AsyncValidator.messages), (key) => {
    let message = AsyncValidator.messages[key];
    return _.isPlainObject(message) ? _.keys(message) : key;
});

AsyncValidator.labels = {name: '名称', password: '密码'};
AsyncValidator.formatMessage = function (fieldsRules, labels, messages) {
    if (!labels) labels = AsyncValidator.labels;
    if (!messages) messages = AsyncValidator.messages;
    for (let field in fieldsRules) {
        let fieldRules = fieldsRules[field];
        if (Array.isArray(fieldRules)) {
            fieldRules.forEach(fieldRule => {
                if (fieldRule.message) return;
                let rule = fieldRule.rule || AsyncValidator.ruleNames.find(t => t in fieldRule);
                let type = fieldRule.type || 'string';
                let message = messages[rule] || messages[type][rule];
                let values = [labels[field]];
                values = values.concat(fieldRule[rule]);
                values.length = message.match(/%s/g).length;
                fieldRule.message = format(message, ...values);
            });
        }
    }
    return fieldsRules;
};

/** copy from iview */
function format() {
    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
        args[_key] = arguments[_key];
    }

    var i = 1;
    var f = args[0];
    var len = args.length;
    if (typeof f === 'function') {
        return f.apply(null, args.slice(1));
    }
    if (typeof f === 'string') {
        var str = String(f).replace(/%[sdj%]/g, function (x) {
            if (x === '%%') {
                return '%';
            }
            if (i >= len) {
                return x;
            }
            switch (x) {
                case '%s':
                    return String(args[i++]);
                case '%d':
                    return Number(args[i++]);
                case '%j':
                    try {
                        return JSON.stringify(args[i++]);
                    } catch (_) {
                        return '[Circular]';
                    }
                    break;
                default:
                    return x;
            }
        });
        for (var arg = args[i]; i < len; arg = args[++i]) {
            str += ' ' + arg;
        }
        return str;
    }
    return f;
}


//--------------axios
//处理参数和数据
axios.defaults.paramsSerializer = function (params) {
    return Qs.stringify(params, {arrayFormat: 'repeat'});
};

axios.defaults.transformRequest.unshift(function (data, headers) {
    return Qs.stringify(data, {arrayFormat: 'repeat'});
});


function ResultHandler(response) {
    return response;
}

ResultHandler.buildSuccessHandler = function (options) {
    let messager = options.messager;
    return function (result) {
        let config = result.config, data = result.data || {};
        data.isSuccess = () => data.code === 'success';
        messager = config.messager || messager;
        if (messager) {
            if (data.isSuccess()) {
                console.info('handle success: ', result);
                config.showSuccessMessage === true && (messager.info(data.message || '操作成功'));
            } else {
                config.showFailureMessage !== false && (messager.warning(data.message || '操作失败'));
            }
        }
        return result.data;
    };
};

ResultHandler.buildSessionExpiredHandler = function (options) {
    let messager = options.messager;
    return function (result) {
        let config = result.config, data = result.data;
        if (data.code === 'sessionExpired') {
            console.info('handle sessionExpired: ', result);
            messager = config.messager || messager;
            if (messager) {
                config.showFailureMessage = false;
                messager.warning(data.message || '会话超时');
            }
        }
        return result;
    };
};


ResultHandler.buildFailureHandler = function (options) {
    let messager = options.messager;
    return function (result) {
        let config = result.config, response = result.response;
        messager = config.messager || messager;
        if (!messager) return result;
        if (response) {
            if (response.status === 404) {
                messager.error(`请求的资源'${config.url}'不存在`);
            } else if (response.status === 500) {
                messager.error('服务器内部异常');
            } else {
                messager.error(response.statusText);
            }
        } else {
            messager.error(result.message || '网络异常');
        }
        return Promise.resolve(result);
    };
};

ResultHandler.register = function (options) {
    axios.interceptors.response.use(ResultHandler.buildSessionExpiredHandler(options), ResultHandler.buildFailureHandler(options));
    axios.interceptors.response.use(ResultHandler.buildSuccessHandler(options), null);
};


//TODO 列表页
//列表上的删除暂时不做
//新增后，列表页数据不更新
//查看过的记录需要有标记
//无法设值排序的初始化状态


//TODO 详情页
//验证提示汉化
//