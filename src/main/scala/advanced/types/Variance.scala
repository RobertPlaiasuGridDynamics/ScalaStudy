package advanced.types

import org.graalvm.compiler.nodeinfo.StructuralInput.Condition

object Variance extends App{
   class Vehicle
   class Bike extends Vehicle
   class Car extends Vehicle

   class IList[T]


   class IParking[T](vehicles: List[T]) {
     def park(vehicle: T) : IParking[T] = ???
     def impound(vehicles : List[T]) : IParking[T] = ???
     def checkVehicles(conditions : String) : List[T] = ???

     def flatMap[S](f : T => IParking[S]) : CParking[S] = ???
   }

   class CParking[+T](vehicles : List[T]) {
     def park[S >: T](vehicle: S) : CParking[S] = ???
     def impound[S >: T](vehicles: List[S]): CParking[S] = ???
     def checkVehicles(conditions : String) : List[T] = ???
     def flatMap[S](f : T => CParking[S]) : CParking[S] = ???
   }

   class XParking[-T](vehicles : List[T]){
     def park(vehicle: T) : XParking[T] = ???
     def impound(vehicles : List[T]) : XParking[T] = ???
     def checkVehicles[S <: T](condition: String): List[S] = ???
     def flatMap[R <: T,S](f : R => XParking[S]) : CParking[S] = ???
   }


  class CParking2[+T](vehicles: List[T]) {
    def park[S >: T](vehicle: S): CParking2[S] = ???

    def impound[S >: T](vehicles: IList[S]): CParking2[S] = ???

    def checkVehicles[S >: T](conditions: String): IList[S] = ???
  }

  class XParking2[-T](vehicles: List[T]) {
    def park(vehicle: T): XParking2[T] = ???

    def impound[S <: T](vehicles: IList[S]): XParking2[S] = ???

    def checkVehicles[S <: T](condition: String): IList[S] = ???
  }




}
