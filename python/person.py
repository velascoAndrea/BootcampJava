class Person:
    def __init__(self, id, name, age):
        self.id = id
        self.name = name
        self.age = age
    
    def __str__(self):
        return f"{self.id} - {self.name} - {self.age}"
    
    def getHalfAge(self):
        return self.age/2