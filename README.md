# EmoSenseNet Neural Interface 🧠⚡

**EmoSenseNet** is a fully functional, frontend-only web application designed to analyze the sentiment of mixed data streams (text + emojis). It processes user input through a simulated neural workflow, extracting features, applying attention weights, and rendering a final sentiment judgment. 

---

## 🚀 Features
* **Dual-Stream Processing:** Separates and analyzes standard text and Unicode emojis independently.
* **Dictionary-Based Extraction:** Uses predefined embedding dictionaries to assign sentiment weights to specific words and emojis.
* **Weighted Fusion:** Combines text and emoji scores using predefined attention weights.
* **Cyberpunk UI:** Immersive dark mode design with neon accents, CRT-style grids, and sequential animations.

---

## ⚙️ How It Works (The Code Logic)
The core logic is contained within the `processInput()` function, following a strict 6-step pipeline:

1. **Input Collection:** Captures the raw string from the user input field.
2. **Text Preprocessing:** Filters out standard ASCII text (code points < 256) to isolate the words.
3. **Emoji Detection:** Filters out higher Unicode characters (code points >= 256) to isolate the emojis.
4. **Feature Extraction:** * Calculates a raw text score based on the `textEmbeddings` dictionary.
   * Calculates a raw emoji score based on the `emojiEmbeddings` dictionary.
5. **Model Evaluation (Fusion):** Applies attention weights to calculate the `Fused Score Z`.
   * *Formula:* `Z = (TextScore * 0.35) + (EmojiScore * 0.65)`
6. **Final Judgment:** Maps the `Fused Score Z` to a definitive sentiment:
   * **Positive:** Z > 0.1
   * **Negative:** Z < -0.1
   * **Neutral:** -0.1 <= Z <= 0.1

---

*this project is collaboratively made by nilayg26 & vasundhararai1803*
