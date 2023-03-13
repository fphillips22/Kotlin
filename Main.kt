
import kotlin.io.*
import kotlin.math.*

data class Peaks(val name: String, val latitude: Double, val longitude: Double)

fun listPeaks(peaks : List<Peaks>): List<Peaks>{
    return peaks
}


fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val r = 6372.8 // in kilometers
    val l1 = Math.toRadians(lat1)
    val l2 = Math.toRadians(lat2)
    val dl = Math.toRadians(lat2 - lat1)
    val dr = Math.toRadians(lon2 - lon1)
    return 2 * r * asin(
        sqrt(
            sin(dl / 2).pow(2.0) +
                    sin(dr / 2).pow(2.0) * cos(l1) * cos(l2)
        )
    )
}

fun distanceFromBozeman(peak: Peaks): Double{
    return haversine(peak.latitude, peak.longitude, 45.6770, -111.0429)
}

val lambda1: (List<Peaks>) -> List<Peaks> = {p -> p.filter { it.longitude < -109.9541 } }
val lambda2: (List<Peaks>) -> List<String> = { peaks ->  peaks.map { it.name }}
val anon = fun(p2: List<Peaks>): List<Peaks> = p2.filter { distanceFromBozeman(it) > 500}


fun main() {
    val peak1 = Peaks("Gallatin Peak", 45.3683, -111.3658)
    val peak2 = Peaks("Granite Peak", 45.1633, -109.8080)
    val peak3 = Peaks("Whitetail Peak", 45.0888, -109.5877)
    val peak4 = Peaks("Pikes Peak", 38.8409, -105.0423)
    val peak5 = Peaks("Mount Rainier", 46.879967, -121.726906)
    val p: List<Peaks> = listOf(peak1,peak2,peak3, peak4, peak5)
    val peakLocations = listPeaks(p)


    println("***STEP TWO***")
    println("Peaks: ")
    println(peakLocations)


    println("***STEP THREE***")
    println("Distance from Bozeman to Whitetail Peak in km: ")
    println(distanceFromBozeman(peak3))


    println("***STEP FOUR***")
    println("A new List with the distances of all Peaks in list to Bozeman: ")
    println(peakLocations.map { distanceFromBozeman(it) })


    println("***STEP FIVE***")
    val p1 = anon(peakLocations)
    for(peak in p1){
        println(peak.name)
    }


    println("***STEP SIX***")
    val p2 = lambda1(peakLocations)
    val p3 = lambda2(p2)
    println(p3)


    println("***STEP SEVEN***")
    println("Max distance: " +peakLocations.maxBy { distanceFromBozeman(it) })
    val mappedPeaks: Map<String, Peaks> = mapOf(
        peak1.name to peak1,
        peak2.name to peak2,
        peak3.name to peak3,
        peak4.name to peak4,
        peak5.name to peak5


    )
    println("***STEP EIGHT***")
    for (key in mappedPeaks){
        println("Name: " +key.key +" "+ key.value)
    }
    if (mappedPeaks.containsKey("Gallatin Peak")){
        mappedPeaks.get(key = "Gallatin Peak")?.let { println("The latitude for Gallatin Peak is "+ it.latitude) }
    }
    





}