using Restaurante.Models;

namespace Restaurante.Service
{
    public interface IMesas
    {
         IEnumerable<TaMesa> GetMesas();
       
        TaMesa ObtenerPorid(int id);

        void add(TaMesa taMesa);
        
        void modificar(TaMesa taMesa);

        void eleminar(int id);
        
    }
}
