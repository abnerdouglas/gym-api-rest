import { FC } from 'react';

interface TextBoxProps {
  value: string;
  onChange: (value: string) => void;
}

const TextBox: FC<TextBoxProps> = ({ value, onChange }) => {
  return <input type="text" value={value} onChange={(e) => onChange(e.target.value)} />;
};

export default TextBox;
