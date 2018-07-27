(function () {

    /*编辑页变详情页*/
    class View {
        constructor(element) {
            this.element = element;
        }

        init() {
            this.resolveType();
            if (this.type === 'form') {
                let elements = this.element.elements;
                console.info('View.init: elements->', elements);
                //需要倒序遍历，当elements中的元素在渲染时被删除会影响elements数组本身
                for (let i = elements.length - 1; i >= 0; i--) {
                    new View(elements[i]).init();
                }
            } else {
                this.render(this.resolveValue());
            }
        }

        resolveType() {
            this.type = this.element.type || this.element.getAttribute('type') || 'form';
            this.type = this.type.toLowerCase();
            console.info('View.resolveType: ', this.type)
        }

        resolveValue() {
            switch (this.type) {
                case 'text':
                case 'textarea':
                    return this.element.value;
                case 'select-one':
                    return this.element.children[this.element.selectedIndex].innerText;
                case 'radio-group':
                    for (let i = 0; i < this.element.children.length; i++) {
                        let child = this.element.children[i];
                        if (child.firstElementChild.checked) {
                            return child.innerText;
                        }
                    }
            }
            return null;
        }

        render(value) {
            console.info('View.render: value->', value);
            if (value === null) return;
            switch (this.type) {
                case 'radio-group':
                    this.element.innerHTML = value;
                    break;
                default:
                    this.element.parentNode.innerHTML = value;
            }
        }
    }

    window.View = View;

})();