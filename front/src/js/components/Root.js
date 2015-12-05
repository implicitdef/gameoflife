const React = require('react');
const _ = require('lodash');
const Matrix = require('./Matrix');

const scala = ScalaMain();
window.scala = scala;
const Displayer = ({message}) => (
  <h1>{message}</h1>
);

const Root = React.createClass({
  getInitialState(){
    return scala.getState();
  },
  componentWillMount(){
    scala.subscribe((state) => {
      this.setState(state);
    });
  },
  render(){
    return <Matrix {...this.state}/>;
  }
});

module.exports = Root;
