/**
 * index.js
 * Created by Huxley on 12/3/15.
 */

import { Component, CORE_DIRECTIVES, FORM_DIRECTIVES, Inject } from 'angular2/angular2';
import { Router } from 'angular2/router';
import { Http, Headers } from 'angular2/http';
import User from 'lib/user';
let template = require('./template.html');
let debug = require('debug')('ng:signup-form');

@Component ({
    selector: 'signup-form',
    template: template,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export default class SignupForm {
    constructor(@Inject(Router) router, @Inject(Http) http) {
        this.router = router;
        this.http = http;
        this.user = new User();
    }
    onSubmit() {
        debug(this.user);
        let headers = new Headers({'Content-Type': 'application/json'});
        this.http.post('/signup', JSON.stringify(this.user), { headers: headers }).subscribe(res => {
            let json = res.json();
            if (json && 'OK' === json.status) {
                this.router.parent.navigateByUrl('/main');
            }
        });
    }
}
