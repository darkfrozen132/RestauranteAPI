using Restaurante.Models;

namespace Restaurante.Service
{
    public interface IUsuario
    {
        IEnumerable<TaUsuario> GetUsuario();

        TaUsuario ObtenerPorid(int id);

        void add(TaUsuario TaUsuario);

        void modificar(TaUsuario TaUsuario);

        void eleminar(int id);

        int verificacion(TaUsuario TaUsuario);
    
    }
}
