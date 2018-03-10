# S2Utilities

This projects aims at providing a set of tools to make working with geospatial objects quick and painless.
These tools were designed with S2 objects (Google's "geometry on a sphere" abstractions) in mind as the leading data structure to be used when working with geospatial data.

[![Build Status](https://travis-ci.org/dmarcous/S2Utilities.svg?branch=master)](http://travis-ci.org/dmarcous/S2Utilities)
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)


## Getting S2Utilites

The current stable version is 1.0.0, which is cross-built against Scala 2.11.x and 2.12.x.

If you're using SBT, add the following line to your build file:

```scala
libraryDependencies += "com.github.dmarcous" %% "s2utils_2.12" % "1.0.0"
```

For Maven and other build tools, you can visit [search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.dmarcous%22%20AND%20a%3A%22s2utils_2.12%22).


## Quick Start

```scala
import com.github.dmarcous.s2utils._

val longitude = 34.777112
val latitude = 32.071801
val s2Level = 14
val s2CellToen = CoordinateConverters.lonLatToS2CellFullToken(longitude, latitude, s2Level)

```

## Resources

API documents in [S2Utilities Scaladocs](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/index.html)

## Packages

S2Utilities contain the following packages :
[converters](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/converters/index.html) - Tools for converting between geo measurement [untis](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/converters/UnitConverters$.html) and [coordinate](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/converters/CoordinateConverters$.html) related objects.
[geo](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/geo/GeographyUtilities$.html) - Tools for handling jts geometry objects with ease.
[s2](https://dmarcous.github.io/S2Utilities/latest/api/com/github/dmarcous/s2utils/s2/S2Utilities$.html) - Tools for handling S2Cells and related S2 family objects.

### converters

UnitConverters - methods for converting between units like degrees-radians, angles-meters.
CoordinateConverters - methods for converting between coordinate related objects like radian/polar-cartesian/degrees, lon/lat-s2cell.

### geo

GeographyUtilities - methods for handling jts geometry objects with ease like WKT/WKB converters, haversina/geosphere distnace computationg, geometry simplifications.

### s2

S2Utilities - methods for handling S2Cells and related S2 family objects like coordinate to cell/cellId/cellToken converters, and providing quick simple access to cell properties like center point, neighbour cells, parent cells.
