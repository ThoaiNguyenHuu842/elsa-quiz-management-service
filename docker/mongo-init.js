db.createUser(
        {
            user: "quiz_user",
            pwd: "quiz_pwd",
            roles: [
                {
                    role: "readWrite",
                    db: "quiz"
                }
            ]
        }
);

db.createCollection("quiz_answers")

db.createCollection("quiz")

db.quiz.createIndex({ quiz_id: 1 })

db.quiz.insert([
   {
     "_id": NumberDecimal("1"),
     "quiz_id": "1a2b3c4d",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the capital of France?",
         "answers": ["Berlin", "Madrid", "Paris", "Rome"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "Which planet is known as the Red Planet?",
         "answers": ["Earth", "Mars", "Jupiter", "Saturn"],
         "correct_answer": 1
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Who wrote 'Romeo and Juliet'?",
         "answers": ["William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"],
         "correct_answer": 0
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("2"),
     "quiz_id": "2b3c4d5e",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the chemical symbol for water?",
         "answers": ["O2", "H2O", "CO2", "NaCl"],
         "correct_answer": 1
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "How many continents are there?",
         "answers": ["5", "6", "7", "8"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Which is the smallest ocean in the world?",
         "answers": ["Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Southern Ocean"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   //invalid quiz cause it is finished already
   {
    "_id": NumberDecimal("3"),
     "quiz_id": "3c4d5e6f",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "Who was the first President of the United States?",
         "answers": ["Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"],
         "correct_answer": 1
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the largest mammal in the world?",
         "answers": ["Elephant", "Blue Whale", "Giraffe", "Rhino"],
         "correct_answer": 1
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "How many colors are there in a rainbow?",
         "answers": ["5", "6", "7", "8"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2024-07-01T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("4"),
     "quiz_id": "4d5e6f7g",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the capital of Japan?",
         "answers": ["Seoul", "Beijing", "Tokyo", "Bangkok"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the speed of light?",
         "answers": ["300,000 km/s", "150,000 km/s", "200,000 km/s", "250,000 km/s"],
         "correct_answer": 0
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Who painted the Mona Lisa?",
         "answers": ["Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("5"),
     "quiz_id": "5e6f7g8h",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "Which is the longest river in the world?",
         "answers": ["Nile", "Amazon", "Yangtze", "Mississippi"],
         "correct_answer": 0
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the largest desert in the world?",
         "answers": ["Sahara", "Gobi", "Kalahari", "Antarctic Desert"],
         "correct_answer": 3
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Who developed the theory of relativity?",
         "answers": ["Isaac Newton", "Galileo Galilei", "Albert Einstein", "Niels Bohr"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("6"),
     "quiz_id": "6f7g8h9i",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the hardest natural substance on Earth?",
         "answers": ["Gold", "Iron", "Diamond", "Platinum"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "How many states are there in the United States?",
         "answers": ["48", "49", "50", "51"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Which element has the chemical symbol 'O'?",
         "answers": ["Oxygen", "Osmium", "Oganesson", "Oxygenium"],
         "correct_answer": 0
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("7"),
     "quiz_id": "7g8h9i0j",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "Which planet is closest to the Sun?",
         "answers": ["Earth", "Venus", "Mercury", "Mars"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the capital of Australia?",
         "answers": ["Sydney", "Melbourne", "Canberra", "Brisbane"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "What is the largest planet in our solar system?",
         "answers": ["Earth", "Saturn", "Jupiter", "Uranus"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("8"),
     "quiz_id": "8h9i0j1k",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the tallest mountain in the world?",
         "answers": ["K2", "Kangchenjunga", "Everest", "Lhotse"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "Who is known as the father of computers?",
         "answers": ["Alan Turing", "Charles Babbage", "John von Neumann", "Steve Jobs"],
         "correct_answer": 1
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "What is the smallest unit of life?",
         "answers": ["Atom", "Molecule", "Cell", "Organ"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("9"),
     "quiz_id": "9i0j1k2l",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "Which country is known as the Land of the Rising Sun?",
         "answers": ["China", "South Korea", "Japan", "Thailand"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the primary ingredient in sushi?",
         "answers": ["Rice", "Noodles", "Bread", "Potatoes"],
         "correct_answer": 0
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Who discovered penicillin?",
         "answers": ["Alexander Fleming", "Marie Curie", "Louis Pasteur", "Gregor Mendel"],
         "correct_answer": 0
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   },
   {
     "_id": NumberDecimal("10"),
     "quiz_id": "0j1k2l3m",
     "questions": [
       {
         "question_id": NumberDecimal("1"),
         "question": "What is the largest organ in the human body?",
         "answers": ["Heart", "Liver", "Skin", "Lungs"],
         "correct_answer": 2
       },
       {
         "question_id": NumberDecimal("2"),
         "question": "What is the boiling point of water?",
         "answers": ["50°C", "75°C", "90°C", "100°C"],
         "correct_answer": 3
       },
       {
         "question_id": NumberDecimal("3"),
         "question": "Who was the first man to step on the moon?",
         "answers": ["Yuri Gagarin", "Buzz Aldrin", "Neil Armstrong", "Michael Collins"],
         "correct_answer": 2
       }
     ],
     "start_time" : ISODate("2024-06-30T14:28:17.585Z"),
     "end_time" : ISODate("2100-08-30T14:28:17.585Z")
   }
  ]
)