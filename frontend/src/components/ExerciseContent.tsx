import React, { createContext, useState, useContext, ReactNode } from 'react';

type Exercise = {
  difficulty: string;
  equipment: string;
  instructions: string;
  muscle: string;
  name: string;
  type: string;
};

type ExerciseContextType = {
  exercises: Exercise[];
  setExercises: React.Dispatch<React.SetStateAction<Exercise[]>>;
};

const ExerciseContext = createContext<ExerciseContextType | undefined>(undefined);


export const ExerciseProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [exercises, setExercises] = useState<Exercise[]>([]);
  
  return (
    <ExerciseContext.Provider value={{ exercises, setExercises }}>
      {children}
    </ExerciseContext.Provider>
  );
};

export const useExercise = () => {
  const context = useContext(ExerciseContext);
  if (!context) {
    throw new Error('useExercise must be used within an ExerciseProvider');
  }
  return context;
};
