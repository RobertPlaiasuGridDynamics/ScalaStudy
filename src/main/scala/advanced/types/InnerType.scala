package advanced.types

object InnerType extends App{
  trait ItemLike {
    type Key
  }
  trait Item[K] extends ItemLike {
    type Key = K
  }

  trait IntItem extends Item[Int]
  trait StringItem extends Item[String]

  // def get[ItemType <: ItemLike](key : Key) :ItemType = ???
  
  // get[IntItem](42)
  // get[StringItem]("home")
  // get[IntItem]("scala")
}
