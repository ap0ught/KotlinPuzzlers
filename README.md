Kotlin Puzzlers
===============

A curated collection of Kotlin code puzzles designed to challenge your understanding of the language and highlight subtle behaviors. Originally arranged for the Insanabyte`18 conference presentation.

Introduction
============

Kotlin Puzzlers is a repository of code snippets and puzzles that demonstrate unexpected or tricky aspects of Kotlin. Each puzzler is intended to spark discussion, deepen understanding, and help developers avoid common pitfalls.

What is a puzzler?
==================

A puzzler is some code that doesn't work the way it seems it should work. These puzzles are great for learning, teaching, and improving your Kotlin skills.

Features
========

- Collection of Kotlin code puzzles with explanations
- Covers language quirks, edge cases, and best practices
- Useful for workshops, interviews, and self-study

How to Use
==========

1. Clone this repository:
   ```
   git clone https://github.com/yourusername/KotlinPuzzlers.git
   ```
2. Browse the puzzlers in the project directory.
3. Try to solve each puzzler before reading the explanation.
4. Run the code samples using your favorite Kotlin IDE or the command line.

Contributing
============

Contributions are welcome! If you have a puzzler to share or want to improve existing ones:

1. Fork the repository.
2. Add your puzzler or enhancement.
3. Submit a pull request with a clear description.

License
=======

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

Contact
=======

For questions, suggestions, or feedback, please open an issue or contact the maintainer.

Thanks:
=======

Anton Keks for good collection: github.com/angryziber/kotlin-puzzlers

Andrey Breslav (@abreslav) and Roman Elizarov (@relizarov) for their lectures

Example Directory and Solution Breakdown
=======================================

Directory: `src/part1/1_whoslogic.kts`

This puzzler demonstrates Kotlin's syntax differences from other languages, specifically the use of the ternary operator (`? :`). The code attempts to use a ternary conditional, which is valid in Java and other C-like languages but not in Kotlin.

Solution:
---------

The code will not compile because Kotlin does not support the ternary operator. Instead, Kotlin uses `if` expressions for conditional logic. The correct way to write this in Kotlin would be:

```kotlin
println(if (yes == "yes") "yes" else "maybe")
```

Why this is a good example:
---------------------------

- Highlights a common mistake for developers transitioning from Java or similar languages to Kotlin.
- Encourages learning Kotlin's idiomatic conditional expressions.
- Demonstrates how language syntax differences can lead to compilation errors.
- Serves as a quick check for understanding Kotlin's control flow constructs.

By including both the puzzler and its solution, this directory helps developers avoid common pitfalls when writing Kotlin code.
