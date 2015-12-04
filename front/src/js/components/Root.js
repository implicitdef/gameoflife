const React = require('react');
const _ = require('lodash');

const scala = ScalaMain();

const Displayer = ({message}) => (
  <h1>{message}</h1>
);

const Root = React.createClass({
  getInitialState(){
    return scala.state;
  },
  componentWillMount(){
    scala.subscribe((state) => {
      this.setState(state);
    });
  },
  render(){
    return <Displayer {...this.state}/>;
  }
});

module.exports = Root;
