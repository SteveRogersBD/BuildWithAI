![image alt](https://github.com/SteveRogersBD/BuildWithAI/blob/0da7ece105704617a95d37ab6463ce1356607f07/WhatsApp%20Image%202024-11-18%20at%2005.11.26.jpeg)
# BuildWithAI: Speechify


# Inspiration

The inspiration behind this project comes from a close friend of mine who cannot hear or speak. I've seen firsthand how challenging it can be for them to communicate with others, especially in situations where sign language isn't understood. Their struggles inspired me to create a tool that could help bridge this communication gap and make their life easier. I wanted to use technology to empower them and others like them, ensuring they can interact with the world more confidently and inclusively.
What It Does

This app is designed to empower individuals who cannot hear or speak by providing tools that enable seamless communication and accessibility. It offers three main features:

    Chat: Facilitates real-time conversations between the user and others. The user can type a message, which the app converts to speech for the other person. In return, spoken responses are transcribed into text, allowing the user to understand and participate in conversations effortlessly.

    Video Assistance: Many videos online lack subtitles, making them inaccessible to individuals with hearing impairments. This feature allows users to input a video link, and the app provides a descriptive summary of the content. This ensures they can engage with videos effectively, breaking barriers in consuming media content.

    Real-Time Transcription: Transcribes conversations or audio in real-time, ideal for watching movies, attending meetings, or engaging in any scenario where understanding spoken words is crucial. Additionally, the user can summarize lengthy transcripts into concise key points with just one click, saving time and effort.

With these features, the app strives to create an inclusive environment, enhancing the everyday experiences of those with hearing and speech disabilities.
How We Built It

    Gemini API: Used for summarizing, translating text, and describing video content from a link.
    Android's Text-to-Speech API: Implemented to convert speech to text and vice versa.
    Multithreading: Utilized to ensure smooth and efficient performance.

Challenges We Ran Into

Integrating the Gemini model was challenging due to the complexity of setting up prompts that would provide relevant replies. We faced issues with receiving irrelevant responses and had to experiment with different prompt structures to achieve accuracy.
Accomplishments That We're Proud Of

    Successfully using Android's Text-to-Speech API for the first time.
    Implementing multithreading for enhanced app performance.

What We Learned

    How to effectively use multithreading to create a responsive application.
    Deeper understanding of integrating APIs and fine-tuning prompt structures for LLMs.

What's Next for Speechify

We plan to integrate an AI model capable of translating sign language into human language in real time and vice versa, enhancing accessibility further. Continued use of the Gemini API for content summarization and translation will also be expanded.


# Technologies Used

    Programming Languages: Java, Kotlin
    Development Tools: Android Studio, Gradle (build tool)
    APIs: Android Text-to-Speech API, Gemini API
    Database: Firebase (authentication and real-time database)

## **Watch the following video to understand it better.**  

[![Watch the Video](https://img.youtube.com/vi/dWoCCekeOmk?si=0KEFnyoejp0tJTf8/0.jpg)](https://youtu.be/dWoCCekeOmk?si=0KEFnyoejp0tJTf8)
