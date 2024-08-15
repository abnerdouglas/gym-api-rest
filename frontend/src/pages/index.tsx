import { useEffect } from 'react';
import { useRouter } from 'next/router';
import { GetServerSideProps } from 'next';

interface HomeProps {
  isAuthenticated: boolean;
}

const Home: React.FC<HomeProps> = ({ isAuthenticated }) => {
  const router = useRouter();

  useEffect(() => {
    if (!isAuthenticated) {
      router.push('/login');
    }
  }, [isAuthenticated, router]);

  if (!isAuthenticated) {
    return null; // ou exibir um indicador de carregamento
  }

  return <div>Bem-vindo à página inicial!</div>;
};

export default Home;

export const getServerSideProps : GetServerSideProps = async (context) => {
  // Verifique a autenticação do usuário (a lógica pode variar)
  const isAuthenticated = context.req.cookies['auth-token'] !== undefined;

  if (!isAuthenticated) {
    return {
      redirect: {
        destination: '/login',
        permanent: false,
      },
    };
  }

  return {
    props: { isAuthenticated },
  };
}
