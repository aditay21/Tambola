package com.aditechnology.tambola

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import kotlin.random.Random

class TicketMainAdapter(private val mainItems: MutableList<String>) :
    RecyclerView.Adapter<TicketMainAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val crossImg: ImageView = itemView.findViewById(R.id.crossImg)
        val numberRecyclerView: RecyclerView = itemView.findViewById(R.id.numberRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.crossImg.visibility= View.INVISIBLE
        val mainItem = mainItems[position]
        holder.titleTextView.text = mainItem


        holder.itemView.setOnLongClickListener {
          //  holder.crossImg.visibility = View.VISIBLE
            true // Indicate that the long click was handled
        }

        holder.crossImg.setOnClickListener {
            mainItems.removeAt(position)
            notifyDataSetChanged()
        }

        // Set up the inner RecyclerView
        val numbers = listOf(2, 22, 42, 55, 72, 9, 27, 43, 67, 87, 10, 39, 44, 69, 73)




        val extendedNumbers = mutableListOf<String>()

        for (i in 0..26) {
            if (i < numbers.size) {
                extendedNumbers.add(numbers[i].toString())
            } else {
                extendedNumbers.add(" ") // Add blank spaces
            }
        }
        var firstRow = mutableListOf<String>()
        var secondRow = mutableListOf<String>()
        var thirdRow = mutableListOf<String>()




        var ran = Random.nextInt(0, 3)
        extendedNumbers[0] = "" + Random.nextInt(1, 4)
        firstRow.add(extendedNumbers[0])
        extendedNumbers[9] = "" + Random.nextInt(4, 7)
        secondRow.add(extendedNumbers[9])
        extendedNumbers[18] = "" + Random.nextInt(7, 10)
        thirdRow.add(extendedNumbers[18])


        if (ran == 0) {
            extendedNumbers[0] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[9] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            extendedNumbers[18] = ""
            thirdRow.removeLast()
        }

        ran = Random.nextInt(0, 3)
        extendedNumbers[1] = "" + Random.nextInt(10, 14)
        extendedNumbers[10] = "" + Random.nextInt(14, 17)
        extendedNumbers[19] = ""+Random.nextInt(17, 20)

        firstRow.add(extendedNumbers[1])
        secondRow.add(extendedNumbers[10])
        thirdRow.add(extendedNumbers[19])

        if (ran == 0) {
            extendedNumbers[1] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[10] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            extendedNumbers[19] = ""
            thirdRow.removeLast()
        }

        ran = Random.nextInt(0, 3)
        extendedNumbers[2] = "" + Random.nextInt(20, 24)
        extendedNumbers[11] = "" + Random.nextInt(24, 27)
        extendedNumbers[20] = ""+Random.nextInt(27, 30)

        firstRow.add(extendedNumbers[2])
        secondRow.add(extendedNumbers[11])
        thirdRow.add(extendedNumbers[20])

        if (ran == 0) {
            extendedNumbers[2] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[11] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            extendedNumbers[20] = ""
            thirdRow.removeLast()
        }
        ran = Random.nextInt(0, 3)
        extendedNumbers[3] = "" + Random.nextInt(30, 34)
        extendedNumbers[12] = "" + Random.nextInt(34, 37)
        extendedNumbers[21] = ""+Random.nextInt(37, 40)

        firstRow.add(extendedNumbers[3])
        secondRow.add(extendedNumbers[12])
        thirdRow.add(extendedNumbers[21])

        if (ran == 0) {
            extendedNumbers[3] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[12] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            thirdRow.removeLast()
            extendedNumbers[21] = ""
        }
        ran = Random.nextInt(0, 3)
        extendedNumbers[4] = "" + Random.nextInt(40, 44)
        extendedNumbers[13] = "" + Random.nextInt(44, 47)
        extendedNumbers[22] = ""+Random.nextInt(47, 50)

        firstRow.add(extendedNumbers[4])
        secondRow.add(extendedNumbers[13])
        thirdRow.add(extendedNumbers[22])


        if (ran == 0) {
            extendedNumbers[4] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[13] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            extendedNumbers[22] = ""
            thirdRow.removeLast()
        }
        ran = Random.nextInt(0, 3)
        extendedNumbers[5] = "" + Random.nextInt(50, 54)
        extendedNumbers[14] = "" + Random.nextInt(54, 57)
        extendedNumbers[23] = ""+Random.nextInt(57, 60)

        firstRow.add(extendedNumbers[5])
        secondRow.add(extendedNumbers[14])
        thirdRow.add(extendedNumbers[23])

        if (ran == 0) {
            firstRow.removeLast()
            extendedNumbers[5] = ""
        }
        if (ran == 1) {
            secondRow.removeLast()
            extendedNumbers[14] = ""
        }
        if (ran == 2) {
            thirdRow.removeLast()
            extendedNumbers[23] = ""
        }
        ran = Random.nextInt(0, 3)

        extendedNumbers[6] = "" + Random.nextInt(60, 64)
        extendedNumbers[15] = "" + Random.nextInt(64, 67)
        extendedNumbers[24] = ""+Random.nextInt(67, 70)

        firstRow.add(extendedNumbers[6])
        secondRow.add(extendedNumbers[15])
        thirdRow.add(extendedNumbers[24])

        if (ran == 0) {
            firstRow.removeLast()
            extendedNumbers[6] = ""
        }
        if (ran == 1) {
            extendedNumbers[15] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            thirdRow.removeLast()
            extendedNumbers[24] = ""
        }

        ran = Random.nextInt(0, 3)
        extendedNumbers[7] = "" + Random.nextInt(70, 74)
        extendedNumbers[16] = "" + Random.nextInt(74, 77)
        extendedNumbers[25] = ""+Random.nextInt(77, 80)

        firstRow.add(extendedNumbers[7])
        secondRow.add(extendedNumbers[16])
        thirdRow.add(extendedNumbers[25])
        if (ran == 0) {
            extendedNumbers[7] = ""
            firstRow.removeLast()
        }
        if (ran == 1) {
            extendedNumbers[16] = ""
            secondRow.removeLast()
        }
        if (ran == 2) {
            thirdRow.removeLast()
            extendedNumbers[25] = ""
        }


        ran = Random.nextInt(0, 3)
        extendedNumbers[8] = "" + Random.nextInt(80, 84)
        extendedNumbers[17] = "" + Random.nextInt(84, 87)
        extendedNumbers[26] = ""+Random.nextInt(87, 90)

        firstRow.add(extendedNumbers[8])
        secondRow.add(extendedNumbers[17])
        thirdRow.add(extendedNumbers[26])

        if (ran == 0) {
            firstRow.removeLast()
            extendedNumbers[8] = ""
        }
        if (ran == 1) {
            secondRow.removeLast()
            extendedNumbers[17] = ""
        }
        if (ran == 2) {
            thirdRow.removeLast()
            extendedNumbers[26] = ""
        }

       /* val temp = mutableListOf<Int>()
        for (i in 0..1){
            val randomNumber = getRandom(temp,extendedNumbers,firstRow,secondRow,thirdRow)
            val index = extendedNumbers.indexOf(randomNumber.toString())
           extendedNumbers[index]=""
            temp.add(randomNumber)
        }*/
        Log.e("TAG", "First Row "+firstRow.size )
        Log.e("TAG", "Second Row "+secondRow.size)
        Log.e("TAG", "Third Row "+thirdRow.size)

        firstRow = deleteItemFromFirstRowIfMorethenFive(firstRow,extendedNumbers,"FirstRow")
        secondRow = deleteItemFromFirstRowIfMorethenFive(secondRow,extendedNumbers,"Second")
        thirdRow =deleteItemFromFirstRowIfMorethenFive(thirdRow,extendedNumbers,"Third Row")

        val ticketItemAdapter = TicketItemAdapter(extendedNumbers)
        holder.numberRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 9)
        holder.numberRecyclerView.adapter = ticketItemAdapter
        val divider = DividerItemDecoration(holder.itemView.context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(Color.GRAY))
        holder.numberRecyclerView.addItemDecoration(divider)

        val horizontalDivider = DividerItemDecoration(holder.itemView.context, DividerItemDecoration.HORIZONTAL)
        horizontalDivider.setDrawable(ColorDrawable(Color.GRAY))
        holder.numberRecyclerView.addItemDecoration(horizontalDivider)
    }

    private fun deleteItemFromFirstRowIfMorethenFive(firstRow: MutableList<String>,mainList: MutableList<String>,string: String) : MutableList<String> {
       Log.e("TAG",string)
        if (firstRow.size > 5) {
            // Generate a random index within the range of the list's size
            // val randomIndex = Random.nextInt(firstRow.size)
            while (firstRow.size > 5){
                Log.e("TAG","in while size :- "+firstRow.size)
            val randomValue = firstRow[Random.nextInt(firstRow.size)]
            // Remove the item at the random index
            //val removedItem = firstRow.removeAt(randomValue)
               firstRow.remove(randomValue)
                Log.e("TAG","Random value "+randomValue)
            val index = mainList.indexOf(randomValue)
            Log.e("TAG", "Index: $index")
            if (index != -1 && mainList.size > index) {
                Log.e("TAG", "Size ${mainList.size} index $index")
                mainList[index] = ""
            }
            // Print the removed item and the updated list
            //   Log.e("TAG", "Removed item: $removedItem")
            Log.e("TAG", "Updated list: $firstRow")
        }
    }
        else {
            println("$string List size is not greater than 5.")
        }
        return firstRow
    }
    private fun deleteItemFromSecondRowIfMorethenFive(secndRow: MutableList<String>,mainList: MutableList<String>): MutableList<String>  {
        if (secndRow.size > 5) {
            // Generate a random index within the range of the list's size
            val randomIndex = Random.nextInt(secndRow.size)

            // Remove the item at the random index
            val removedItem = secndRow.removeAt(randomIndex)

            val index =    mainItems.indexOf(removedItem)
            Log.e("TAG", "Index: $index")
            if (index !=-1 && mainList.size>index) {
                Log.e("TAG","Size ${mainList.size} index $index")
                mainList[index] = ""
            }
            // Print the removed item and the updated list
            Log.e("TAG", "Removed item: $removedItem")
            Log.e("TAG", "Updated list: $secndRow")
        } else {
            println("Second List size is not greater than 5.")
        }
        return secndRow
    }

    private fun deleteItemFromThirdRowIfMorethenFive(third: MutableList<String>,mainList: MutableList<String>) : MutableList<String> {
        if (third.size > 5) {
            // Generate a random index within the range of the list's size
            val randomIndex = Random.nextInt(third.size)

            // Remove the item at the random index
            val removedItem = third.removeAt(randomIndex)
            val index =    mainItems.indexOf(removedItem)
            Log.e("TAG", "Index: $index")
            if (index !=-1 && mainList.size>index) {
                Log.e("TAG","Size ${mainList.size} index $index")
                mainList[index] = ""
            }

            // Print the removed item and the updated list
            Log.e("TAG", "Removed item: $removedItem")
            Log.e("TAG", "Updated list: $third")
        } else {
            println("Third List size is not greater than 5.")
        }
        return third
    }

    private fun getRandom(temp: MutableList<Int>,permanentList: MutableList<String>
    ,firstRow:MutableList<String>,secndRow :MutableList<String>,thirdRow :MutableList<String>
    ): Int {

        val tempNumber = Random.nextInt(0, 27)
        if (!permanentList.contains(tempNumber.toString()) ){

            Log.e("TAG","274 "+tempNumber)
            return getRandom(temp, permanentList, firstRow, secndRow, thirdRow)
        }

        Log.e("TAG","273 "+tempNumber)
        Log.e("TAG", "First Row "+firstRow.size +"   "+firstRow.contains(tempNumber.toString()))
        Log.e("TAG", "Second Row "+secndRow.size+" "+secndRow.contains(tempNumber.toString()))
        Log.e("TAG", "Third Row "+thirdRow.size+""+thirdRow.contains(tempNumber.toString()))
           if (firstRow.contains(tempNumber.toString()) && firstRow.size <4) {
               Log.e("TAG","284")
                return getRandom(temp, permanentList, firstRow, secndRow, thirdRow)
            }
            if (secndRow.contains(tempNumber.toString()) && secndRow.size < 4) {
                Log.e("TAG","288")
                return getRandom(temp, permanentList, firstRow, secndRow, thirdRow)
            }
            if (thirdRow.contains(tempNumber.toString()) && thirdRow.size < 4) {
                Log.e("TAG","292")
                return getRandom(temp, permanentList, firstRow, secndRow, thirdRow)
            }
        Log.e("TAG","292")


        if (tempNumber in temp || permanentList[tempNumber].isEmpty()) {
            return getRandom(temp, permanentList, firstRow, secndRow, thirdRow)
        } else {
            if (firstRow.contains(tempNumber.toString())){
                firstRow.remove(tempNumber.toString())
            }
            if (secndRow.contains(tempNumber.toString())){
                secndRow.remove(tempNumber.toString())
            }
            if (thirdRow.contains(tempNumber.toString())){
                thirdRow.remove(tempNumber.toString())
            }
            Log.e("TAG","Random Number return $tempNumber")
            return tempNumber
        }
    }

    override fun getItemCount(): Int {
        return mainItems.size
    }

}