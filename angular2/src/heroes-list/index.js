/**
 * Created by zl on 2015/12/13.
 */
import {Component,Inject} from "angular2/core"
import {Router,RouteParams,RouterLink} from 'angular2/router'
@Component({
    template: require('./template.html'),
    directives:[RouterLink]
})
export default class {
    constructor() {
        //this.selectId = routerParams.get('id');
    }

    heroes = [
        {name: 'test', age: 10}, {name: 'test2', age: 20}
    ];

    onSelect(hero) {
        this.router.navigate(['HeroesDetail', {id: hero.name}])
    }

}
