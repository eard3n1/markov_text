## markov_text
Markov chain text generator with a Python script to measure cosine similarity.

## Features
- Generates text using n-gram Markov chains
- Customizable n-gram size, sentence length, number of sentences, and seed word
- Saves generated sentences to: <code>data/generated.txt</code>
- Measures similarity between input and generated text using TF-IDF + cosine similarity.
- Provides basic analysis like most common next words for seeds.

## Requirements
- Java 11+
- Python 3.5+
- scikit-learn

## Usage
1. Generate Text:

    - Navigate to the src directory:
        ```bash
        cd src
        ```

    - Compile Java code:
        ```bash
        javac Main.java MarkovGenerator.java
        ```

    - Run the generator:
        ```bash
        java Main
        ```

    - Enter:
        1. n-gram size (2–5 recommended)
        2. Max sentence length
        3. Number of sentences
        4. Optional seed word

    - Generated sentences are saved in data/generated.txt.

2. Measure Similarity:
    - From root:
        ```bash
        python infer.py
        ```

    - This outputs a cosine similarity score (0–1) between input.txt and generated.txt.


## Tips
- Smaller n-grams (2–3) produce more creative, less coherent text.
- Larger n-grams (4–5) produce more coherent text, closer to the original style.
- You can replace input.txt with any text to experiment.

## License
MIT License