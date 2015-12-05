const React = require('react');

const Cell = React.createClass({
  shouldComponentUpdate(nextProps, nextState){
    return nextProps.content != this.props.content;
  },
  render(){
    const {content} = this.props;
    return <td>{content}</td>
  }
});



const Matrix = ({rows}) => {
  return (<table>
    <tbody>
      {
        rows.map((row, i) => {
          return (<tr key={i}>
            {
              row.map((cell, j) => {
                return <Cell key={j} content={cell}/>;
              })
            }
          </tr>);
        })
      }
    </tbody>
  </table>);
};

module.exports = Matrix;
