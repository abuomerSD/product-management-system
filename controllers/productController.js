const Pool = require('pg').Pool;


// create a database connection
const pool = new Pool({
        user: 'asdf',
        host: 'localhost',
        database: 'productDB',
        password: '',
        port: 5432,
      });

const getAllProducts = (req, res) => {
    pool.query('SELECT * FROM product', (error, results) => {
       if(error) {
           throw error;
       }
       res.json(results.rows);
   
})
}

const getSingleProduct = (req, res) => {
    const id = req.params.id;
    const sql = 'select * from product where id = $1';
    pool.query(sql,[id], (err, result)=> {
        if(err){
            console.log(err.message);
        }
        res.json(result.rows[0]);
    })
}

const saveProduct = (req, res) => {
    const sql = 'insert into product (pname, price) values($1, $2)'
    const body = req.body;
    const values = [body.pname, body.price];

    pool.query(sql, values)
        .then( ()=> res.json(body))
        .catch( (err) => res.json({err: err.stack}));
}

const updateProduct = (req, res) => {
    const sql = 'update product set pname =$1, price = $2 where id =$3';
    const id = req.params.id;
    const body = req.body;
    const values = [body.pname, body.price, id];
    pool.query(sql, values)
        .then( () => res.json(body) )
        .catch( (err) => res.json({err: err.stack}));
    
}

const deleteProduct = (req, res) => {
    const sql = 'delete from product where id = $1';
    const id = req.params.id;
    pool.query(sql,[id])
        .then( () => res.json({id}) )
        .catch( (err) => res.json({err: err.stack}));
    
}

module.exports = { getAllProducts,
                   getSingleProduct,
                   saveProduct,
                   updateProduct,
                   deleteProduct }