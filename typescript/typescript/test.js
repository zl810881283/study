var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
/**
 * Created by zl on 2015/11/10.
 */
var test = 10;
console.log(test);
var TestClass = (function () {
    function TestClass(a, b) {
        this.a = a;
        this.b = b;
    }
    TestClass.prototype.add = function () {
        return this.a + this.b;
    };
    TestClass.prototype.test = function () {
        return this.a - this.b;
    };
    return TestClass;
})();
var ChildClass = (function (_super) {
    __extends(ChildClass, _super);
    function ChildClass(a, b, c, d) {
        _super.call(this, a, b);
        this.c = c;
        this.d = d;
    }
    return ChildClass;
})(TestClass);
if (new ChildClass(1, 2, 3, 4) instanceof TestClass)
    console.log("aaa");
var testClass = new TestClass(1, 2);
console.log(testClass.add());
function funTest(a, b) {
    return (a + b).toString();
}
console.log(funTest(test, test));
//# sourceMappingURL=test.js.map