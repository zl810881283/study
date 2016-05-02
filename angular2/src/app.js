/**
 * Created by zl on 2015/12/13.
 */
import {Component,Inject} from 'angular2/core';
import {bootstrap} from 'angular2/bootstrap';
import {Router,RouteConfig,ROUTER_DIRECTIVES,ROUTER_PROVIDERS,RouteParams} from 'angular2/router';
import {HTTP_PROVIDERS,Http} from 'angular2/http'

import Header from 'header';

import HeroesList from 'heroes-list';
import HeroesDetail from 'heroes-detail';
import Page2 from 'page2';
import Page3 from 'page3';


let debug = require('debug')('ng:app');


@RouteConfig([
    {path: '/heroes', component: HeroesList, as: 'HeroesList', useAsDefault: true},
    {path: '/heroes/:id', component: HeroesDetail, as: 'HeroesDetail'},
    {path: '/page2', component: Page2, as: 'Page2'},
    {path: '/page3', component: Page3, as: 'Page3'}
])
@Component({
    selector: 'app',
    template: require('./template.html'),
    styles: [require('./style')],
    directives: [Header, ROUTER_DIRECTIVES]
})
class App {
    constructor( router:Router,http:Http) {
        this._route=router;
        this._http=http;
        console.log(router);
        console.log(http);
        console.log('adsfsd')
    }
}
bootstrap(App, [ROUTER_PROVIDERS,HTTP_PROVIDERS]);