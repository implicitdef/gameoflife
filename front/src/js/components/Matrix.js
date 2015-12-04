const React = require('react');

const Matrix = ({rows}) => {
  return (<table>
    <tbody>
      {
        rows.map((row, i) => {
          return (<tr key={i}>
            {
              row.map((cell, j) => {
                return <td key={j}>{cell}</td>;
              })
            }
          </tr>);
        })
      }
    </tbody>
  </table>);
};

module.exports = Matrix;
