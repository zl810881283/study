/**
 * webpack.config.js
 * Created by Huxley on 11/30/15.
 */
var webpack = require('webpack');
var path = require('path');
module.exports = {
    entry: {
        ng2: [
            'angular2/bundles/angular2-polyfills',
            'angular2/bootstrap',
            'angular2/common',
            'angular2/core',
            'angular2/router',
            'angular2/http'
        ],
        app: path.join(__dirname, 'src', 'app.component.js')
    },
    output: {
        path: path.join(__dirname, 'build'),
        publicPath: '/build/',
        filename: '[name].bundle.js'
    },
    module: {
        loaders: [
            {test: /\.html$/, loader: 'raw'},
            {test: /\.json$/, loader: 'json'},
            {test: /\.(png|bmp|jpg)$/, loader: 'url', query: {limit: 8192}},

            {
                test: /\.jsx?$/, loader: 'babel',
                exclude: /node_modules/,
                query: {
                    stage: 1,
                    plugins: ['typecheck', 'angular2-annotations']
                }
            },
            {test: /\.less$/, loader: 'raw!autoprefixer!less'},
            {test: /\.scss$/, loader: 'raw!autoprefixer!sass'}
        ]
    },
    resolve: {
        extensions: ['', '.json', '.js', '.jsx', '.less'],
        modulesDirectories: ['node_modules', path.join(__dirname, 'src')]
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
        contentBase: path.join(__dirname),
        publicPath: '/build/'
    }
};
