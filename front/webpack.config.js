var path = require("path");
var webpack = require("webpack");
module.exports = {
  entry: "./src/js/app.js",
  output: {
      path: path.join(__dirname, "dist"),
      filename: "bundle.js"
  },
  module: {
    loaders: [
      {
        test: /\.s?css$/,
        loaders: ["style", "css?sourceMap", "sass?sourceMap"]
      },
      {
        test: /\.js?$/,
        exclude: /(node_modules|scala)/,
        loader: 'babel',
        query: {
            cacheDirectory: true,
            presets: ['es2015', 'react']
        }
      },
      {
        test: /\.(png|jpg)$/,
        loader: 'url-loader'
      }
    ]
  },
  devServer: {
    contentBase: path.join(__dirname, "dist")
  }
};
