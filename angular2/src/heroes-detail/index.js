/**
 * Created by zl on 2015/12/13.
 */
import {Component,Inject} from "angular2/core"
import {Router,RouteParams} from 'angular2/router'
import {Http} from 'angular2/http'
@Component({
    template: require('./template.html')
})
export default class {
    constructor() {
    //@Inject(Router) router:Router, @Inject(RouteParams) routeParams:RouteParams
    //    this.router = router;
    //    this.heroId = routeParams.get('id');
    //    console.log(heroId);
    }
}
