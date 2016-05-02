import {Inject,Component} from "angular2/core";
import {CORE_DIRECTIVES} from 'angular2/common'
import {HTTP_PROVIDERS,Http} from 'angular2/http'
import {Router,ROUTER_PROVIDERS,Route} from 'angular2/router'
import {bootstrap} from "angular2/bootstrap";

class Mock {
    constructor() {
        this.id = Math.random();
        console.log("generate ... " + this.id);
    }
}
@Component({
    selector: "ez",
    providers: [HTTP_PROVIDERS, ROUTER_PROVIDERS,Mock],
    template: "<div>{{name}} : {{mock.id}}</div>",
    directives: [CORE_DIRECTIVES],
})
class Ez {
    constructor(mock:Mock,router:Router,http:Http) {
        this.mock = mock;
        this.name = 'xxx';
        console.log('begin to load Ez')
        console.log(http);
        console.log(router);
    }
}

@Component({
    selector: "app",
    template: `
		<h1>EZ-APP</h1>
		<ez></ez>
		<ez></ez>
	`,
    directives: [Ez,CORE_DIRECTIVES]
})
class EzApp {
    constructor( router:Router,http:Http) {
        console.log(http);
        console.log(router);
    }
}

bootstrap(EzApp, [HTTP_PROVIDERS, ROUTER_PROVIDERS])

