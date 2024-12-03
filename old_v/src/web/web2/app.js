// app.js (Express and EJS setup)
const express = require('express');
const app = express();
app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended: true }));

let projects = []; // In-memory data for simplicity

app.get('/', (req, res) => {
  res.render('index', { projects });
});

app.get('/createProject', (req, res) => {
  res.render('createProject');
});

app.post('/saveProject', (req, res) => {
  const { name, description } = req.body;
  projects.push({ name, description });
  res.redirect('/');
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
