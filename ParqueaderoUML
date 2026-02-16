@startuml

class Vehiculo {
  - placa : String
  - marca : String
  - modelo : String
  - horaEntrada : LocalDateTime
  + calcularCosto() : double
  + getTiempoEstadia() : long
}

class Automovil {
  - tipoCombustible : String
  + calcularCosto() : double
}

class Motocicleta {
  - cilindraje : int
  + calcularCosto() : double
}

class Camion {
  - capacidadCarga : double
  + calcularCosto() : double
}

class Parqueadero {
  - nombre : String
  - listaVehiculos : List<Vehiculo>
  + registrarEntrada(v: Vehiculo)
  + registrarSalida(placa: String) : double
  + consultarEstado()
}

Vehiculo <|-- Automovil
Vehiculo <|-- Motocicleta
Vehiculo <|-- Camion
Parqueadero "1" o-- "0..*" Vehiculo

@enduml
