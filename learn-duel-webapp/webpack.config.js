"use strict";

const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    plugins: [new VueLoaderPlugin()],
    stats: 'minimal',
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
            },
            {
                test: /\.scss$/,
                exclude: /node_modules/,
                use: [
                    'vue-style-loader',
                    'css-loader',
                    'sass-loader'
                ],
            },
            {
                test: /\.ts$/,
                loader: 'ts-loader',
                options: {
                    appendTsSuffixTo: [/\.vue$/],
                }
            }
        ]
    },
    resolve: {
        extensions: ['.ts', '.js', '.vue']
    }
};