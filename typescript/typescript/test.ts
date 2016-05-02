/**
 * Created by zl on 2015/11/10.
 */
var test:number=10;

console.log(test);

class TestClass{
    a:number;
    b:number;
    constructor(a:number,b:number){
        this.a=a;
        this.b=b;
    }
    add():number{
        return this.a+this.b;
    }
    test():number{
        return this.a-this.b;
    }
}
class ChildClass extends TestClass{
    c:number;
    d:number;
    constructor(a:number,b:number,c:number,d:number){
        super(a,b);
        this.c=c;
        this.d=d;
    }
}
if(new ChildClass(1,2,3,4) instanceof TestClass)
    console.log("aaa");

var testClass:TestClass=new TestClass(1,2);
console.log(testClass.add());

function funTest(a:number,b:number) : string{
    return (a+b).toString();
}
console.log(funTest(test,test));
