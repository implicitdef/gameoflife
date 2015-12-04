require("../css/style.scss");
require('../../../scala/target/scala-2.11/gameoflife-fastopt.js')
const $ = require('jquery');
const ReactDOM = require('react-dom');
const React = require('react');
const Root = require('./components/Root');

$(() => {
  ScalaMain().sayHello();
  ReactDOM.render(
    <Root/>,
    $('.root').get(0)
  );
});
