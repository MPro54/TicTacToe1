package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentPlayer = 1
    private var nextPlayer = 1
    private var gameActive = true
    private var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2) // chaques cellules sont a 2
            // Vide = 2
            //    X = 1
            //    O = 0
    private var scorePlayer1 = 0
    private var scorePlayer2 = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewHome()
    }

    fun viewHome() {
        // Cacher les le jeux le temps que les joueurs écrivent leur noms
        binding.gridCell.visibility = View.GONE
        binding.btnRestart.visibility = View.GONE
        binding.btnQuit.visibility = View.GONE
        binding.txtPlayerTurn.visibility = View.GONE
        binding.txtScore1.text = "$scorePlayer1"
        binding.txtScore2.text = "$scorePlayer2"
    }
    fun checkPlayers(view: View){

        //Pour valider que les noms des joueurs ne sont pas vides et qu'ils sont différents
        val player1Name = binding.player1.text.toString().trim()
        val player2Name = binding.player2.text.toString().trim()

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            Toast.makeText(this, "Les noms des joueurs ne peuvent pas être vides", Toast.LENGTH_SHORT).show()
            return
        }

        if (player1Name == player2Name) {
            Toast.makeText(this, "Les noms des joueurs doivent être différents", Toast.LENGTH_SHORT).show()
            return
        }
        loadGame()
    }
    fun loadGame() {
        //Pour afficher le jeux et pour cacher les éléments d'acceuil
         binding.gridCell.visibility = View.VISIBLE
         binding.btnRestart.visibility = View.VISIBLE
         binding.btnQuit.visibility = View.VISIBLE
         binding.txtPlayerTurn.visibility = View.VISIBLE
         binding.txtmsg.visibility = View.GONE
         binding.btnStart.visibility = View.GONE

        // Initialiser les cellules vides
        val cells = arrayOf(binding.cell1, binding.cell2, binding.cell3, binding.cell4, binding.cell5, binding.cell6, binding.cell7, binding.cell8, binding.cell9)
        for (cell in cells) {
            cell.setImageResource(R.drawable.none)
            gameActive = true
        }

        //Pour vérrouiller les noms des joueurs
        binding.player1.isEnabled = false
        binding.player2.isEnabled = false

        // Afficher le nom du joueur a jouer
        if (currentPlayer == 1) {
            binding.txtPlayerTurn.text = "C'est le tour de ${binding.player1.text}"
        } else {
            binding.txtPlayerTurn.text = "C'est le tour de ${binding.player2.text}"
        }

     }
    fun cellClicked(view: View) {
        // La vue passée en paramètre est convertie en ImageView
        val cell = view as ImageView
        //converti en entier, soustrait pour correspondre à l'indexation du tableau gameState
        val cellId = resources.getResourceEntryName(cell.id).takeLast(1).toInt()

        // Si le jeu n'est pas actif ou si la cellule sur laquelle l'utilisateur a cliqué n'est pas vide (indiqué par la valeur 2 dans gameState), la fonction retourne et ne fait rien
        if (!gameActive || gameState[cellId - 1] != 2) {
            return
        }
        // Met à jour l'état et l'image de la cellule avec le joueur actuel
        gameState[cellId - 1] = currentPlayer
        cell.setImageResource(if (currentPlayer == 1) R.drawable.cross else R.drawable.circle)

        // Détermine le prochain joueur et met à jour le texte de txtPlayerTurn pour indiquer quel joueur doit jouer ensuite
        val nextPlayer = if (currentPlayer == 1) binding.player2.text else binding.player1.text
        binding.txtPlayerTurn.text = "C'est le tour de $nextPlayer"

        // Change le joueur actuel pour le prochain tour
        currentPlayer = 1 - currentPlayer

        // Vérifie si un joueur a gagné
        checkWinner()
    }
    fun checkWinner() {
        //Toutes les combinaisons gagnantes possibles dans un jeu de tic tac toe
        val winningPositions = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        // Parcourir toutes les combinaisons gagnantes
        for (winningPosition in winningPositions) {
            // Si toutes les cellules d'une combinaison gagnante sont remplies par le même joueur et ne sont pas vides
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                gameState[winningPosition[0]] != 2)
            {
                // Mettre fin au jeu
                gameActive = false

                // Si le joueur 1 a gagné, il début la prochaine partie et son score est mis a jour
                if (gameState[winningPosition[0]] == 1) {
                    nextPlayer = 1
                    scorePlayer1++
                    binding.txtScore1.text = "$scorePlayer1"
                    binding.txtPlayerTurn.text = "Le gagnant est ${binding.player1.text} !!!\nC'est au tour de ${binding.player1.text}..."

                }
                // Si le joueur 2 a gagné, il début la prochaine partie et son score est mis a jour
                else {
                    scorePlayer2++
                    nextPlayer = 0
                    binding.txtScore2.text = "$scorePlayer2"
                    binding.txtPlayerTurn.text = "Le gagnant est ${binding.player2.text} !!!\n C'est au tour de ${binding.player2.text}..."
                }
                // Quittez la fonction car un joueur a gagné
                return

            }
        }
        // Vérifiez s'il reste des cellules non remplies
        if (gameState.contains(2)) {
            // Le jeu continue
        } else {
            // C'est un match nul
            val nextPlayerName = if (nextPlayer == 1) binding.player1.text else binding.player2.text
            binding.txtPlayerTurn.text = "C'est un match nul !!! \nC'est au tour de $nextPlayerName..."
        }
    }
    fun restartGame(view:View) {
        if(gameActive && gameState.contains(2)){
            // boîte de dialogue pour demander à l'utilisateur s'il veut vraiment recommencer la partie
            val dialog = AlertDialog.Builder(this)
                .setTitle("Nouveau départ !")
                .setMessage("Voulez-vous vraiment recommencer ? \nLa partie en cours sera perdue...")
                .setPositiveButton("Oui") { _, _ ->
                    // Réinitialisez l'état du jeu
                    gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
                    gameActive = true
                    currentPlayer = nextPlayer

                    // Mettez à jour l'interface utilisateur
                    val cells = arrayOf(
                        binding.cell1,
                        binding.cell2,
                        binding.cell3,
                        binding.cell4,
                        binding.cell5,
                        binding.cell6,
                        binding.cell7,
                        binding.cell8,
                        binding.cell9
                    )
                    for (cell in cells) {
                        cell.setImageResource(R.drawable.none)
                    }

                }

                .setNegativeButton("Non", null)
                .create()

            // Affichez la boîte de dialogue
            dialog.show()
        }
        else
        {
            // Réinitialisez l'état du jeu
            gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
            gameActive = true
            currentPlayer = nextPlayer

            // Mettez à jour l'interface utilisateur
            val cells = arrayOf(
                binding.cell1,
                binding.cell2,
                binding.cell3,
                binding.cell4,
                binding.cell5,
                binding.cell6,
                binding.cell7,
                binding.cell8,
                binding.cell9)
            for (cell in cells) {
                cell.setImageResource(R.drawable.none)
            }

        }

    }
    fun quitGame(view:View){
        // Créez une boîte de dialogue pour demander à l'utilisateur s'il veut vraiment quitter la partie
        val dialog = AlertDialog.Builder(this)
            .setTitle("Quitter la partie")
            .setMessage("Êtes-vous sûr de vouloir quitter la partie ? \nLa partie en cours sera perdue...")
            .setPositiveButton("Oui") { _, _ ->
                // Réinitialisez l'état du jeu
                gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
                gameActive = false
                binding.txtmsg.visibility = View.VISIBLE

                // Mettez à jour l'interface utilisateur
                val cells = arrayOf(
                    binding.cell1,
                    binding.cell2,
                    binding.cell3,
                    binding.cell4,
                    binding.cell5,
                    binding.cell6,
                    binding.cell7,
                    binding.cell8,
                    binding.cell9
                )
                for (cell in cells) {
                    cell.setImageResource(R.drawable.none)
                }
                //Réinitialisation du jeu
                scorePlayer1 = 0
                scorePlayer2 = 0
                binding.player1.setText("")
                binding.player2.setText("")
                binding.player1.isEnabled = true
                binding.player2.isEnabled = true
                binding.btnStart.visibility = View.VISIBLE
                viewHome()
            }
            .setNegativeButton("Non", null)
            .create()

        // Affichez la boîte de dialogue
        dialog.show()
    }
}
