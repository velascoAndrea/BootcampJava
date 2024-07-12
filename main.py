from flask import Flask, jsonify, request

app = Flask(__name__)
app.config["DEBUG"] = True

@app.route('/', methods=['GET'])
def main():
    return "hola", 200

@app.route('/employee', methods=['GET'])
def getEmployees():
    employees = ['luis', 'felipe']
    return jsonify(employees), 200

@app.route('/employee/<id>', methods=['GET'])
def getEmployeeById(id):
    employee = {
        "id": id,
        "name": "Felipe",
        "age": 35
    }
    area = request.args.get("area")
    if area:
        employee["area"] = area
    return jsonify(employee), 200

@app.route('/employee', methods=['POST'])
def createEmployee():
    data = request.get_json()
    return jsonify(data), 201

app.run()