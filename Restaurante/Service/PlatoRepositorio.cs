using Restaurante.Models;

namespace Restaurante.Service
{
    public class PlatoRepositorio : IPlato
    {
        private Conexion conex = new Conexion();
        public void add(TaPlato taPlato)
        {
              conex.TaPlatos.Add(taPlato);  
            conex.SaveChanges();
        }

        public void eleminar(int id)
        {
            conex.TaPlatos.Remove(ObtenerPorid(id)); 
            conex.SaveChanges();    
        }

        public IEnumerable<TaPlato> GetPlato()
        {
             return conex.TaPlatos; 
        }

        public void modificar(TaPlato taPlato)
        {
            var obj = conex.TaPlatos.Where(tpro => tpro.IdPlato == taPlato.IdPlato)
            .FirstOrDefault();

            obj.NombrePlato = taPlato.NombrePlato;
            obj.TaPedidoDetalles = taPlato.TaPedidoDetalles;
            obj.Costo = taPlato.Costo;  
            obj.Disponible = taPlato.Disponible;    
            conex.SaveChanges();
        }

        public TaPlato ObtenerPorid(int id)
        {
            return conex.TaPlatos.Where(tpro => tpro.IdPlato == id).FirstOrDefault();
        }
    }
}
