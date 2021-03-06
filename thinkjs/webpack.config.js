/**
 * webpack.config.js
 * Created by Huxley on 11/30/15.
 */
var webpack = require('webpack');
var path = require('path');
module.exports = {
    entry: {
        ng2: [
            'reflect-metadata',
            'zone.js',
            'angular2/core',
            'angular2/angular2',
            'angular2/router',
            'angular2/http'
        ],
        app: path.join(__dirname, 'www','static','app.js')
    },
    output: {
        path: path.join(__dirname ,'www','static','build'),
        filename: '[name].bundle.js'
    },
    module: {
        loaders: [
            {test: /\.html$/, loader: 'raw'},
            {test: /\.json$/, loader: 'json'},
            {test: /\.ts$/, loader: 'ts'},
            {
                test: /\.jsx?$/, loader: 'babel',
                exclude: /node_modules/,
                query: {
                    stage: 1,
                    plugins: ['typecheck', 'angular2-annotations']
                }
            },
            {test: /\.scss$/, loader: 'raw!autoprefixer!sass'}
        ]
    },
    resolve: {
        extensions: ['', '.json', '.js', '.jsx'],
        modulesDirectories: ['node_modules', path.join(__dirname, 'www','static')]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'ng2',
            minChunks: Infinity
        })
    ],
    devServer: {
        inline: true,
        colors: true,
        historyApiFallback: true,
        contentBase: path.join(__dirname, 'www','static'),
        publicPath: '/static/build/'
    }
};
