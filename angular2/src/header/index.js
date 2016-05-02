/**
 * Created by zl on 2015/12/13.
 */
import {Component,Inject} from 'angular2/core'
import {RouterLink} from 'angular2/router'
@Component({
    selector: 'header',
    template: require('./template.html'),
    styles: [require('./style')],
    directives: [RouterLink]
})
export default class {
    constructor(@Inject(Http) http:Http){
        this.http=http;
    }
    items = [
        {
            name: 'HeroesList',
            link: ['HeroesList']
        },
        {
            name: 'page2',
            link: ['Page2']
        },
        {
            name: 'page3',
            link: ['Page3']
        }]
}