const Pool = require('pg').Pool
const pool = new Pool({
  user: 'asdf',
  host: 'localhost',
  database: 'testPG',
  password: '',
  port: 5432,
});


const getAllProducts = pool.query('SELECT * FROM product', (error, results) => {
    if(error) {
        throw error;
    }
    return results.rows;
});

module.exports = { getAllProducts }