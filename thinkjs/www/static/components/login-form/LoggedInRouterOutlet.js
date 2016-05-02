/**
 * index.js.js
 * Created by Huxley on 12/2/15.
 */
import { Directive, Attribute, ElementRef, DynamicComponentLoader, Inject } from 'angular2/angular2';
import { Router, RouterOutlet, ComponentInstruction } from 'angular2/router';

@Directive({
    selector: 'custom-router-outlet'
})
export default class LoggedInRouterOutlet extends RouterOutlet {
    constructor(@Inject(ElementRef) _elementRef, @Inject(DynamicComponentLoader) _loader,
                @Inject(Router) _parentRouter, @Attribute('name') nameAttr) {
        super(_elementRef, _loader, _parentRouter, nameAttr);

        this.parentRouter = _parentRouter;
        this.publicRoutes = {
            '/login': true,
            '/signup': true
        };
    }

    activate(instruction: ComponentInstruction) {
        var url = this.parentRouter.lastNavigationAttempt;
        if (!this.publicRoutes[url] && !(localStorage.getItem('userToken') && localStorage.getItem('userToken') !== -1)) {
            this.parentRouter.navigateByUrl('/login');
        }
        return super.activate(instruction);
    }
}