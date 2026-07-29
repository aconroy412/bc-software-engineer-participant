Which parts of a Copilot prompt are untrusted from the model’s perspective, and which are trusted (your business rules)?
    All AI generated content should be treated as unsafe and undergo a human-seated manual verification process.
Where is human review formally enforced before AI code reaches the shared repo?
    It is enforced through pull requests.
Which values must never appear in Chat, even as examples?
    API keys, secrets, user data
What can be safely regenerated if rejected, and what must a human write from scratch?
    Simple code, less than 10 lines with rudamentary logic. Security-sensitive code which controls business logic should be written from scratch. THe point of AI is to automate mundane tasks
What if an AI-suggested dependency only fails in CI mvn compile, not locally?
    manually check the dependency, research on your own.
What would a tech lead audit to confirm AI-assisted code met the same bar as hand-written code?
    Maintainability and correctness. Would also compare hand-written code's readability.
Which licensing/IP concern applies to large verbatim-looking suggestions, and how do you mitigate it?
    Don't copy verbatim. If you are suspicious, check the AI's source or use a paid AI agent.
How do you keep an audit trail of what a human verified vs what the AI produced?
    Engineers must document what they have written with AI and what they did themselves.