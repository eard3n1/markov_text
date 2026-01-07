## markov_similarity
Markov chain text generator made in Java with a Python script that uses <a href="https://scikit-learn.org/stable/">scikit-learn</a> to measure cosine similarity.

## Features
- Generates text using n-gram Markov chains
- Measures similarity between input and generated text using cosine similarity
- Customizable n-gram size, sentence length, number of sentences & seed word/sentence
- Provides the most common next words for seeds if available
- Saves generated sentences to: <code>data/generated.txt</code>

## Requirements
- Java 11+
- Python 3.5+
- scikit-learn

## Setup & Usage
1. **Generate Text:**
    1. Compile the code:
        ```bash
        cd src
        javac Main.java MarkovGenerator.java
        ```

    2. Run the generator:
        ```bash
        java Main
        ```

    3. Enter:
        1. n-gram size
        2. Max sentence length
        3. Number of sentences
        4. Optional seed word/sentence

    4. Generated sentences are saved in: <code>data/generated.txt</code>

2. **Measure Similarity:**
    1. From root:
        ```bash
        python infer.py
        ```
    2. This outputs a cosine similarity score (0–1) between <code>input.txt</code> & <code>generated.txt</code>
<br>

> [!TIP]
> - Smaller n-grams (2–3) produce more creative, less coherent text
> - Larger n-grams (4–5) produce more coherent text, closer to the original style
> - The seed is not designed to be deterministic, its impact is fundementally stoachastic
> - The seed is dependent on the n-gram (n - 1) so for an n-gram of 3 its best to provide 2 words 
> - You can replace the <code>input.txt</code> preset with any text of your likings to experiment
<br>

## License
MIT License
