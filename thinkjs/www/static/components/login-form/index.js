/**
 * login.js
 * Created by Huxley on 12/1/15.
 */

import { Component, CORE_DIRECTIVES, FORM_DIRECTIVES, Inject } from 'angular2/angular2';
import { Router, ROUTER_DIRECTIVES } from 'angular2/router';
import { Http, Headers } from 'angular2/http';
import User from 'lib/user';
let template = require('./template.html');
let debug = require('debug')('ng:login-form');

@Component({
    selector: 'login-form',
    template: template,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export default class LoginForm {
    constructor(@Inject(Router) router, @Inject(Http) http) {
        this.router = router;
        this.http = http;
        this.user = new User();
    }
    onSubmit() {
        let header = new Headers({'Content-Type': 'application/json'});
        this.http.post('/auth', JSON.stringify(this.user), { headers: header }).subscribe(res => {
            let json = res.json();
            if (json) {
                localStorage.setItem('userToken', json.token);
                this.router.parent.navigateByUrl('/main');
            }
        });
    }
}

