import { FC } from 'react';

interface ButtonProps {
  onClick: () => void;
  label: string;
}

const Button: FC<ButtonProps> = ({ onClick, label }) => {
  return <button onClick={onClick}>{label}</button>;
};

export default Button;
