/**
 * Created by zl on 2015/10/18.
 */
var express = require('express');
var router = express.Router();

router.get('/test', function(req, res, next) {
    req.local={};
    req.local.arr=['a','b','c','d','e','f'];
    res.jsonp(req.local);
});

module.exports = router;
