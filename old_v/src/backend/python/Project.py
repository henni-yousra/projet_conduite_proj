from flask import Flask, render_template, request, redirect

app = Flask(__name__)
projects = []

@app.route('/')
def index():
    return render_template('../../web/web2/index.html', projects=projects)

@app.route('/createProject')
def create_project():
    return render_template('../../web/web2/createProject.html')

@app.route('/saveProject', methods=['POST'])
def save_project():
    name = request.form['name']
    description = request.form['description']
    projects.append({'name': name, 'description': description})
    return redirect('/')

if __name__ == '__main__':
    app.run(debug=True)
