/**
 * Created by Huxley on 11/30/15.
 */
import { bootstrap, Component, CORE_DIRECTIVES, Inject } from 'angular2/angular2';
import { Router, ROUTER_DIRECTIVES, ROUTER_PROVIDERS, RouteConfig } from 'angular2/router';
import { HTTP_PROVIDERS } from 'angular2/http';
import LoggedInRouterOutlet from 'components/login-form/LoggedInRouterOutlet';
import Main from 'components/main';
import LoginForm from 'components/login-form';
import SignupForm from 'components/signup-form';

@RouteConfig([
    { path: '/', redirectTo: ['Main'] },
    { path: '/main', as: 'Main', component: Main },
    { path: '/login', as: 'Login', component: LoginForm },
    { path: '/signup', as: 'Signup', component: SignupForm }
])
@Component({
    selector: 'app',
    template: '<custom-router-outlet></custom-router-outlet>',
    directives: [LoggedInRouterOutlet]
})
class App {
    constructor(@Inject(Router) router) {
        this.router = router;
    }
}

bootstrap(App, [ROUTER_PROVIDERS, HTTP_PROVIDERS]);
