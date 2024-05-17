# Online Library Application

## Project Overview

The project focuses on creating an online library application that allows registration of various types of customers. Customers can browse and access books or vinyl records, add them to their shopping cart, and perform various search and management operations.

## Classes Used

**Model Classes**
- **Author class**
- **Publisher class**
- **LibraryItem class**
- **Book class**
- **Vinyl class**
- **Customer class**
- **Member class**
- **Regular class**
- **Student class**
- **Cart class**

**Utility Classes**
- **Item interface**
- **Section enum class**

## Project Structure

The project is structured into several types of classes: model, management, and service.

**Model Classes**
These classes represent the core entities of the application, such as authors, publishers, items, customers, and shopping carts.

**Utility Classes**
- **Item interface**: Defines methods for checking item stock and rating.
- **Section enum class**: Represents different sections/categories for items.

**Management Classes**
These classes handle actions on lists or maps with different types of objects.

**Service Classes**
These classes call methods from the management classes and log actions and their timestamps in a CSV file.

## Class Descriptions

**Author Class**
Represents authors with attributes like name, nationality, and birth year. Supports CRUD operations and nationality-based search.

**Publisher Class**
Represents publishers with attributes like name and a list of associated authors. Supports CRUD operations and author management.

**LibraryItem Class**
Superclass for Book and Vinyl classes, with general attributes and methods inherited by them.

**Book Class**
Represents books with attributes like author, section, publisher, and publication year. Supports CRUD operations and search functionalities.

**Vinyl Class**
Represents vinyl records with attributes like singer, genre, and edition type. Supports CRUD operations and search functionalities.

**Customer Classes**
- **Customer class**: Base class with general customer attributes.
- **Member class**: Subclass of Customer with additional subscription-related attributes.
- **Regular class**: Subclass of Customer representing regular customers.
- **Student class**: Subclass of Customer representing student customers.

**Cart Class**
Represents a shopping cart with attributes for customer, list of books, and list of vinyl records. Supports CRUD operations, total price calculation, and finding popular items.

## Main Class
Implements an interactive menu to navigate through all implemented functionalities easily.

