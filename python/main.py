from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config["DEBUG"] = True
app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+mysqlconnector://root:Velasco2018$@localhost/people"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False

db = SQLAlchemy(app)

class Employee(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    email = db.Column(db.String(255), nullable=False)

    def __repr__(self):
        return f'<Employee {self.name}>'

@app.route('/', methods=['GET'])
def main():
    return "hola", 200

@app.route('/employee', methods=['GET'])
def getEmployees():
    employees = Employee.query.all()
    employee_list = []
    for employee in employees:
        employee_data = {
            "id": employee.id,
            "name": employee.name,
            "email": employee.email
        }
        employee_list.append(employee_data)
    return jsonify(employee_list), 200

@app.route('/employee/<int:id>', methods=['GET'])
def getEmployeeById(id):
    employee = Employee.query.get(id)
    if employee is None:
        return jsonify({"message": "employee not found", "type": "NotFound"}), 404
    employee_data = {
        "id": employee.id,
        "name": employee.name,
        "email": employee.email
    }
    return jsonify(employee_data), 200

@app.route('/employee', methods=['POST'])
def createEmployee():
    data = request.get_json()
    if not data or not 'name' in data or not 'email' in data:
        return jsonify({"message": "Bad request", "type": "BadRequest"}), 400

    new_employee = Employee(name=data['name'], email=data['email'])
    db.session.add(new_employee)
    db.session.commit()
    return jsonify({"id": new_employee.id, "name": new_employee.name, "email": new_employee.email}), 201

if __name__ == '__main__':
    app.run()
