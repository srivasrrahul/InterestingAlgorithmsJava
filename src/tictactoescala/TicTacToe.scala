package tictactoescala

class TicTacToeStore(val _n : Int) {
  val state = Array.ofDim[Int](_n,_n)
  for (j <- 0 to _n-1) {
    for (k <- 0 to _n-1) {
      state(j)(k) = 0
    }
  }

  def isColOne(col : Int,id : Int) : Boolean = {
    var colOne = true
    for (j <- 0 to _n-1 if colOne == true) {
      if (state(j)(col) != id) {
        colOne = false
      }
    }

    colOne
  }

  def isRowOne(row : Int, id : Int) : Boolean = {
    var rowOne = true
    for (j <- 0 to _n-1 if rowOne == true) {
      if (state(row)(j) != id) {
        rowOne = false
      }
    }

    rowOne
  }

  def countForwardDiag(row : Int,col : Int,id : Int) : Int = {
    var j = row
    var k = col
    var forwardDiagCount = 0
    while (j < _n && k < _n) {
      if (state(j)(k) == id) {
        forwardDiagCount = forwardDiagCount + 1
      }

      j= j + 1
      k = k + 1
    }

    forwardDiagCount
  }

  def countBackwardDiag(row : Int,col : Int,id : Int) : Int = {
    var j = row
    var k = col
    var backwardDiagCount = 0
    while (j >=0 && k >= 0) {
      if (state(j)(k) == id) {
        backwardDiagCount = backwardDiagCount+1
      }

      j= j -1
      k = k - 1
    }

    backwardDiagCount
  }

  def isForwardUpperDiag(row : Int,col : Int,id : Int) : Int = {
    var j = row
    var k = col
    var forwardUpperDiagCount = 0
    while (j >= 0 && k < _n) {
      if (state(j)(k) == id) {
        forwardUpperDiagCount = forwardUpperDiagCount+1
      }

      j= j -1
      k = k + 1
    }

    forwardUpperDiagCount
  }

  def isForwardLowerDiag(row : Int,col : Int,id : Int) : Int = {
    var j = row
    var k = col
    var forwardLowerDiagCount = 0
    while (j < _n && k >= 0) {
      if (state(j)(k) == id) {
        forwardLowerDiagCount = forwardLowerDiagCount+1
      }

      j= j +1
      k = k - 1
    }

    forwardLowerDiagCount
  }




  def set(row : Int,col : Int,id : Int) : Unit = {
    state(row)(col) = id
  }

  def isWon(row : Int,col : Int, id : Int) : Boolean = {
    if (isRowOne(row,id) ||
      isColOne(col,id)) {
      true
    }else {
      val c1 = isForwardUpperDiag(row,col,id) + isForwardLowerDiag(row,col,id) - 1
      val c2 = countForwardDiag(row,col,id) + countBackwardDiag(row,col,id) - 1
      c1 == _n || c2 == _n
    }
  }
}

class TicTacToe(_n: Int) {

  /** Initialize your data structure here. */
  val state = new TicTacToeStore(_n)

  /** Player {player} makes a move at ({row}, {col}).
  @param row The row of the board.
  @param col The column of the board.
  @param player The player, can be either 1 or 2.
  @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
  def move(row: Int, col: Int, player: Int): Int = {
    player match {
      case 1 => {
        state.set(row, col,1)
        if (state.isWon(row,col,1)) {
          1
        }else {
          0
        }
      }
      case 2 => {
        state.set(row,col,2)
        if (state.isWon(row,col,2)) {
          2
        }else {
          0
        }
      }
    }

  }

}
