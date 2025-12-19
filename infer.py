from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

with open("data/input.txt", "r", encoding="utf-8") as f:
    input_text = f.read()

with open("data/generated.txt", "r", encoding="utf-8") as f:
    generated_text = f.read()

vectorizer = TfidfVectorizer()
vectors = vectorizer.fit_transform([input_text, generated_text])
similarity = cosine_similarity(vectors[0], vectors[1])

print(f"Cosine similarity: {similarity[0][0]:.4f}")