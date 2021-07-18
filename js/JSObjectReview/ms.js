'use strict';

var _createClass = function () {
    function defineProperties(target, props) {
        for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || false;
            descriptor.configurable = true;
            if ("value" in descriptor) descriptor.writable = true;
            Object.defineProperty(target, descriptor.key, descriptor);
        }
    }

    return function (Constructor, protoProps, staticProps) {
        if (protoProps) defineProperties(Constructor.prototype, protoProps);
        if (staticProps) defineProperties(Constructor, staticProps);//静态属性绑定到函数对象中
        return Constructor;
    };
}();

function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
        throw new TypeError("Cannot call a class as a function");//防止将够着函数作为一般函数使用
    }
}

var Student = function () {
    function Student(name, age) {
        _classCallCheck(this, Student);

        this.name = name;
        this.age = age;
    }

    _createClass(Student, [{
        key: 'getName',
        value: function getName() {
            return this.name;
        }
    }, {
        key: 'getAge',
        value: function getAge() {
            return this.age;
        }
    }]);

    return Student;
}();

var stu = new Student('yang', 19);