from flask import Flask, jsonify, request
from person import Person  # Importando la clase Person

app = Flask(__name__)
app.config["DEBUG"] = True

@app.route('/', methods=['GET'])
def main():
    return "hola", 200

@app.route('/employee', methods=['GET'])
def getEmployees():
    # Crear instancias de Person para cada empleado
    employees = [
        Person(1, 'Luis', 30),
        Person(2, 'Felipe', 35)
    ]
    # Convertir cada objeto Person a un diccionario para la respuesta JSON
    employees_info = [vars(e) for e in employees]
    return jsonify(employees_info), 200

@app.route('/employee/<int:id>', methods=['GET'])
def getEmployeeById(id):
    # Simular la obtención de un empleado por ID
    employee = Person(id, "Felipe", 35)
    area = request.args.get("area")
    if area:
        # Convertir el objeto Person a un diccionario y agregar 'area'
        employee_info = vars(employee)
        employee_info["area"] = area
    else:
        employee_info = vars(employee)
    return jsonify(employee_info), 200

@app.route('/employee', methods=['POST'])
def createEmployee():
    data = request.get_json()
    # Crear un nuevo objeto Person usando los datos recibidos
    new_employee = Person(data["id"], data["name"], data["age"])
    return jsonify(vars(new_employee)), 201

app.run()
